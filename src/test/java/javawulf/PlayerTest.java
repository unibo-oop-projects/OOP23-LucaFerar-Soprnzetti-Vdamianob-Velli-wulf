package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import javawulf.model.player.Player;
import javawulf.model.player.PlayerHealthImpl;
import javawulf.model.player.PlayerImpl;
import javawulf.model.player.ScoreImpl;
import javawulf.model.item.ItemFactoryImpl;

/**
 * The class PlayerTest it used to check if the implementation
 * of Player works according to the game rules.
 */
public final class PlayerTest {

    private final int health = 3;
    private final int startingX = 12;
    private final int startingY = 12;
    private final int startingPoints = 0;
    private Player player;
    private Coordinate test;

    @BeforeEach
    void createPlayer() {
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
        Coordinate expectedCoordinate = new CoordinateImpl(player.getPosition().getX()
            + (int) (movementDirection.getX() * delta), player.getPosition().getY()
            + (int) (movementDirection.getY() * delta));
        BoundingBox expectedBoundingBox = new BoundingBoxImpl(expectedCoordinate.getX(),
            expectedCoordinate.getY(), AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE, CollisionType.PLAYER);
        player.move(movementDirection);
        assertNotEquals(test.getPosition(), player.getPosition().getPosition());
        assertEquals(expectedCoordinate.getPosition(), player.getPosition().getPosition());
        assertEquals(expectedBoundingBox.getCollisionArea(), player.getBounds().getCollisionArea());
    }

    @Test
    void testAttack() {
        CollisionType original = player.getSword().getBounds().getCollisionType();
        CollisionType expected = CollisionType.SWORD;
        assertEquals(original, player.getSword().getBounds().getCollisionType());
        player.attack();
        assertNotEquals(original, player.getSword().getBounds().getCollisionType());
        assertEquals(expected, player.getSword().getBounds().getCollisionType());
    }

    @Test
    void testGettingHit() {
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
        assertEquals(new PlayerHealthImpl(health - 1).getHealth(), player.getPlayerHealth().getHealth());

        assertFalse(player.isHit(enemy));
        for (int i = 4; i > 0; i--) {
            this.player.reduceStun();
            assertEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());
            assertFalse(player.isHit(enemy));
        }
        this.player.reduceStun();
        assertNotEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());
        assertEquals(CollisionType.PLAYER, player.getBounds().getCollisionType());
        assertTrue(player.isHit(enemy));
    }

    @Test
    void testObtainFragment() {
        final int wrongResult = 5;
        List<AmuletPiece> fragments = new ArrayList<>();
        AmuletPiece fragment = new ItemFactoryImpl().createAmuletPiece(test);
        for (int i = 0; i < 4; i++) {
            fragments.add(fragment);
            player.collectAmuletPiece(fragments.get(i));
            assertEquals(i + 1, player.getPieces().size());
        }
        assertThrows(IllegalStateException.class, () -> player.collectAmuletPiece(fragment));
        assertNotEquals(wrongResult, player.getPieces().size());
    }
}
