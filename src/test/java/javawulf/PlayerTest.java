package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.BoundingBox;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.Entity;
import javawulf.model.item.AmuletFragments;
import javawulf.model.player.*;
import javawulf.model.player.Sword.SwordType;

public class PlayerTest {

    int health = 3;
    int startingX = 12;
    int startingY = 12;
    int startingPoints = 0;
    Player player;
    Coordinate test;

    @BeforeEach
    void createPlayer(){
        this.player = new PlayerImpl(startingX, startingY, health, startingPoints);
        this.test = new CoordinateImpl(startingX, startingY);
    }

    @Test
    void testStartingPlayerStatistics() {
        assertEquals(test.getPosition(), player.getPosition().getPosition());
        assertEquals(new BoundingBoxImpl(startingX, startingY, Entity.OBJECT_SIZE, Entity.OBJECT_SIZE,
                CollisionType.PLAYER).getCollisionArea(), player.getBounds().getCollisionArea());
        assertTrue(new PlayerHealthImpl(health).equals(player.getPlayerHealth()));
        assertEquals(SwordType.NORMAL, player.getSword().getSwordType());
        assertTrue(new ScoreImpl(startingPoints).equals(player.getScore()));
        assertEquals(0, player.getFragments().size());
    }

    @Test
    void testPlayerMovement() {
        Direction movementDirection = Direction.DOWN_LEFT;
        int delta = Entity.MOVEMENT_DELTA;
        Coordinate expectedCoordinate = new CoordinateImpl(player.getPosition().getX() +
            (int) (movementDirection.getX()*delta), player.getPosition().getY() +
            (int) (movementDirection.getY()*delta));
        BoundingBox expectedBoundingBox = new BoundingBoxImpl(expectedCoordinate.getX(),
            expectedCoordinate.getY(), Entity.OBJECT_SIZE, Entity.OBJECT_SIZE, CollisionType.PLAYER);
        player.move(movementDirection);
        assertNotEquals(test.getPosition(), player.getPosition().getPosition());
        assertEquals(expectedCoordinate.getPosition(), player.getPosition().getPosition());
        assertEquals(expectedBoundingBox.getCollisionArea(), player.getBounds().getCollisionArea());
    }

    @Test
    void testAttack(){
        CollisionType original = player.getSword().getBounds().getCollisionType();
        CollisionType expected = CollisionType.SWORD;
        assertEquals(original, player.getSword().getBounds().getCollisionType());
        player.attack();
        assertNotEquals(original, player.getSword().getBounds().getCollisionType());
        assertEquals(expected, player.getSword().getBounds().getCollisionType());
    }

    @Test
    void testGettingHit(){
        BoundingBox item = new BoundingBoxImpl(startingX, startingY, Entity.OBJECT_SIZE,
            Entity.OBJECT_SIZE, CollisionType.COLLECTABLE);
        assertFalse(player.isHit(item));
        assertNotEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());

        BoundingBox enemy = new BoundingBoxImpl(startingX, startingY, Entity.OBJECT_SIZE,
            Entity.OBJECT_SIZE, CollisionType.ENEMY);
        assertTrue(player.isHit(enemy));
        assertEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());
        assertFalse(new PlayerHealthImpl(health).equals(player.getPlayerHealth()));
        assertEquals(new PlayerHealthImpl(health-1).getHealth(), player.getPlayerHealth().getHealth());
    }

    @Test
    void testObtainFragment(){
        List<AmuletFragments> fragments = new ArrayList<>();
        AmuletFragments fragment = new AmuletFragments(test, startingPoints);
        for (int i = 0; i < 4; i++) {
            fragments.add(fragment);
            player.collectAmuletPiece(fragments.get(i));
            assertEquals(i+1, player.getFragments().size());
        }
        assertThrows(IllegalStateException.class, () -> player.collectAmuletPiece(fragment));
        assertNotEquals(5, player.getFragments().size());
    }
}
