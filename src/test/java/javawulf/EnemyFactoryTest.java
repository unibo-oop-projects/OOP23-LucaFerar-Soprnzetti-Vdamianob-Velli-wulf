package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.AbstractEntity;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.enemy.Enemy;
import javawulf.model.enemy.EnemyFactory;
import javawulf.model.enemy.EnemyFactoryImpl;
import javawulf.model.enemy.Pawn;

/**
 * Test class for EnemyFactory.
 */
public final class EnemyFactoryTest {

    private static final int STARTING_X = 12;
    private static final int STARTING_Y = 12;

    private EnemyFactory factory;
    private Coordinate position = new CoordinateImpl(STARTING_X, STARTING_Y);

    @BeforeEach
    void setUpFactory() {
        factory = new EnemyFactoryImpl();
    }

    @Test
    void testCreatePawn() {
        Pawn pawn = factory.createPawn(position);
        assertNotNull(pawn);
        assertEquals(position.getPosition(), pawn.getPosition().getPosition());
        // Check if the pawn is instantiated as a Pawn
        assertTrue(pawn instanceof Enemy);
        // Check the coordinates of the pawn
        assertEquals(STARTING_X, pawn.getPosition().getX());
        assertEquals(STARTING_Y, pawn.getPosition().getY());
        // Check if the pawn is alive
        assertTrue(pawn.isAlive());
        // Check the BoundingBox of the pawn
        assertEquals(pawn.getBounds().getCollisionType(), new BoundingBoxImpl(STARTING_X, STARTING_Y,
                AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE, CollisionType.ENEMY).getCollisionType());
        assertEquals(pawn.getBounds().getCollisionArea(), new BoundingBoxImpl(STARTING_X, STARTING_Y,
                AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE, CollisionType.ENEMY).getCollisionArea());
        // Check pawn's speed
        assertEquals(AbstractEntity.DEFAULT_SPEED, pawn.getSpeed());
        // Check the pawn's tick is set to 0
        assertEquals(0, pawn.getTickCount());

    }

}
