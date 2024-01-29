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
import javawulf.model.AbstractEntity;
import javawulf.model.item.AmuletPiece;
import javawulf.model.player.*;

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
        assertEquals(new BoundingBoxImpl(startingX, startingY, AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE,
                CollisionType.PLAYER).getCollisionArea(), player.getBounds().getCollisionArea());
        assertTrue(new PlayerHealthImpl(health).equals(player.getPlayerHealth()));
        assertTrue(new ScoreImpl(startingPoints).equals(player.getScore()));
        assertEquals(0, player.getPieces().size());
    }

    @Test
    void testPlayerMovement() {
        Direction movementDirection = Direction.DOWN_LEFT;
        int delta = AbstractEntity.MOVEMENT_DELTA;
        Coordinate expectedCoordinate = new CoordinateImpl(player.getPosition().getX() +
            (int) (movementDirection.getX()*delta), player.getPosition().getY() +
            (int) (movementDirection.getY()*delta));
        BoundingBox expectedBoundingBox = new BoundingBoxImpl(expectedCoordinate.getX(),
            expectedCoordinate.getY(), AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE, CollisionType.PLAYER);
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
        BoundingBox item = new BoundingBoxImpl(startingX, startingY, AbstractEntity.OBJECT_SIZE,
            AbstractEntity.OBJECT_SIZE, CollisionType.COLLECTABLE);
        assertFalse(player.isHit(item));
        assertNotEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());

        BoundingBox enemy = new BoundingBoxImpl(startingX + AbstractEntity.OBJECT_SIZE, startingY + AbstractEntity.OBJECT_SIZE,
            AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE, CollisionType.ENEMY);
        assertFalse(player.isHit(enemy));
        enemy = new BoundingBoxImpl(startingX, startingY, AbstractEntity.OBJECT_SIZE,
            AbstractEntity.OBJECT_SIZE, CollisionType.ENEMY);
        assertTrue(player.isHit(enemy));
        assertEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());
        assertFalse(new PlayerHealthImpl(health).equals(player.getPlayerHealth()));
        assertEquals(new PlayerHealthImpl(health-1).getHealth(), player.getPlayerHealth().getHealth());

        assertFalse(player.isHit(enemy));
    }

    @Test
    void testObtainFragment(){
        List<AmuletPiece> fragments = new ArrayList<>();
        AmuletPiece fragment = new AmuletPiece(test, startingPoints);
        for (int i = 0; i < 4; i++) {
            fragments.add(fragment);
            player.collectAmuletPiece(fragments.get(i));
            assertEquals(i+1, player.getPieces().size());
        }
        assertThrows(IllegalStateException.class, () -> player.collectAmuletPiece(fragment));
        assertNotEquals(5, player.getPieces().size());
    }
}
