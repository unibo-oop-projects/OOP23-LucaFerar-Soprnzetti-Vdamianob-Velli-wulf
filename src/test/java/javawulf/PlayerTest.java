package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.BoundingBox;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.Entity;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.item.AmuletFragments;
import javawulf.model.player.*;
import javawulf.model.player.Sword.SwordType;

public class PlayerTest {

    int health = 3;
    int startingX = 10;
    int startingY = 10;
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
        assertEquals(player.getPosition().getPosition(), test.getPosition());
        assertEquals(player.getBounds().getCollisionArea(),
            new BoundingBoxImpl(startingX, startingY, Entity.OBJECT_SIZE, Entity.OBJECT_SIZE,
                CollisionType.PLAYER).getCollisionArea());
        assertTrue(new PlayerHealthImpl(health).equals(player.getPlayerHealth()));
        assertEquals(player.getSword().getSwordType(), SwordType.NORMAL);
        assertTrue(new ScoreImpl(startingPoints).equals(player.getScore()));
        assertEquals(player.getFragments().size(), 0);
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
        assertNotEquals(player.getPosition().getPosition(), test.getPosition());
        assertEquals(player.getPosition().getPosition(), expectedCoordinate.getPosition());
        assertEquals(player.getBounds().getCollisionArea(), expectedBoundingBox.getCollisionArea());
    }

    @Test
    void testAttack(){
        CollisionType original = player.getSword().getBounds().getCollisionType();
        CollisionType expected = CollisionType.SWORD;
        assertEquals(player.getSword().getBounds().getCollisionType(), original);
        player.attack();
        assertNotEquals(player.getSword().getBounds().getCollisionType(), original);
        assertEquals(player.getSword().getBounds().getCollisionType(), expected);
    }

    @Test
    void testGettingHit(){
        BoundingBox item = new BoundingBoxImpl(startingX, startingY, Entity.OBJECT_SIZE,
            Entity.OBJECT_SIZE, CollisionType.COLLECTABLE);
        assertFalse(player.isHit(item));
        assertNotEquals(player.getBounds().getCollisionType(), CollisionType.STUNNED);

        BoundingBox enemy = new BoundingBoxImpl(startingX, startingY, Entity.OBJECT_SIZE,
            Entity.OBJECT_SIZE, CollisionType.ENEMY);
        assertTrue(player.isHit(enemy));
        assertEquals(player.getBounds().getCollisionType(), CollisionType.STUNNED);
        assertFalse(new PlayerHealthImpl(health).equals(player.getPlayerHealth()));
        assertEquals(new PlayerHealthImpl(health-1).getHealth(), player.getPlayerHealth().getHealth());
    }
}
