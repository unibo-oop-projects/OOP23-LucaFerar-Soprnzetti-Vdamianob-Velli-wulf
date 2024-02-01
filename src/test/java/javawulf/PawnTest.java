package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.enemy.EnemyFactory;
import javawulf.model.enemy.EnemyFactoryImpl;
import javawulf.model.enemy.Pawn;
import javawulf.model.map.Map;
import javawulf.model.map.factory.MapFactoryImpl;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;

public final class PawnTest {

    private static final int STARTING_X = 24;
    private static final int STARTING_Y = 24;
    private static final int STARTING_HEALTH = 3;

    EnemyFactory factory = new EnemyFactoryImpl();
    Player player = new PlayerImpl(STARTING_X * 3, STARTING_Y, STARTING_HEALTH, 0);
    Coordinate position = new CoordinateImpl(STARTING_X, STARTING_Y);
    Map map = new MapFactoryImpl().getTestMap();
    Pawn pawn;

    @BeforeEach
    void createPawn() {
        pawn = factory.createPawn(position);
    }

    @Test
    void testMove() throws InterruptedException {
        // Test coordinates
        Coordinate testPos = pawn.getPosition();
        // Move the pawn
        pawn.setDirection(Direction.DOWN);
        pawn.move(player, map);
        // Test if the pawn moved
        assertNotEquals(testPos, pawn.getPosition());

        // Position the player in the pawn's path
        player.getPosition().setPosition(pawn.getPosition().getX() + (int) pawn.getDirection().getX(),
                pawn.getPosition().getY() + (int) pawn.getDirection().getY());
        // Taking the pawn's direction
        Direction testDir = pawn.getDirection();
        // Move the pawn
        pawn.move(player, map);

        // Test if the player is hit
        assertTrue(player.isHit(pawn.getBounds()));

        // Test if the pawn changed direction
        assertNotEquals(testDir, pawn.getDirection());

        // Move away the player
        player.getPosition().setPosition(STARTING_X * 10, STARTING_Y);

        // Put the pawn near a wall
        pawn.setPosition(testPos);
        pawn.getPosition().setPosition(testPos.getX() + STARTING_X, testPos.getY());
        pawn.setDirection(Direction.UP);

        // Check if the pawn changes direction after colliding with a wall
        pawn.move(player, map);
        assertNotEquals(Direction.UP, pawn.getDirection());

    }

    @Test
    void testTakeHit() {
        /*
         * Put the player and the pawn near each other so that the player can hit the
         * pawn
         */
        player.getPosition().setPosition(pawn.getPosition().getX() + STARTING_X * 2,
                pawn.getPosition().getY());

        // Hit the pawn
        player.attack();

        // Test if the pawn is hit
        assertTrue(pawn.isHit(player.getSword().getBounds()));

        // Take the hit
        pawn.takeHit(player);

        // Test if the pawn is dead
        assertFalse(pawn.isAlive());
        assertEquals(CollisionType.INACTIVE, pawn.getBounds().getCollisionType());

        // Check if the player got the points
        assertNotEquals(0, player.getScore().getPoints());

    }

}
