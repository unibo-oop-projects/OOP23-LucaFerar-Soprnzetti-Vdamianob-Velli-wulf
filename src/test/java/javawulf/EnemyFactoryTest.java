package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.AbstractEntity;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.enemy.EnemyFactory;
import javawulf.model.enemy.EnemyFactoryImpl;
import javawulf.model.enemy.Guard;
import javawulf.model.enemy.Pawn;

public class EnemyFactoryTest {

    int startingX = 12;
    int startingY = 12;

    EnemyFactory factory;
    Coordinate position = new CoordinateImpl(startingX, startingY);

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
        assertTrue(pawn instanceof Pawn);
        // Check the coordinates of the pawn
        assertEquals(startingX, pawn.getPosition().getX());
        assertEquals(startingY, pawn.getPosition().getY());
        // Check if the pawn is alive
        assertTrue(pawn.isAlive());
        // Check the BoundingBox of the pawn
        assertEquals(pawn.getBounds(), new BoundingBoxImpl(startingX, startingY,
                AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE, CollisionType.ENEMY));
        // Check pawn's speed
        assertEquals(AbstractEntity.DEFAULT_SPEED, pawn.getSpeed());

    }

    @Test
    void testCreateGuard() {
        Guard guard = factory.createGuard(position);
        assertNotNull(guard);
        assertEquals(position.getPosition(), guard.getPosition().getPosition());
        // Check if the guard is instantiated as a Guard
        assertTrue(guard instanceof Guard);
        // Check the coordinates of the guard
        assertEquals(startingX, guard.getPosition().getX());
        assertEquals(startingY, guard.getPosition().getY());
        // Check if the guard is alive
        assertTrue(guard.isAlive());
        // Check if the guard is not stunned
        assertFalse(guard.isStunned());
        // Check the BoundingBox of the guard
        assertEquals(guard.getBounds(), new BoundingBoxImpl(startingX, startingY,
                AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE, CollisionType.ENEMY));
        // Check guard's speed
        assertEquals(AbstractEntity.DEFAULT_SPEED, guard.getSpeed());
    }

}
