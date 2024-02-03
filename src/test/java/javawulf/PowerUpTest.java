package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerHealth;
import javawulf.model.player.PlayerImpl;
import javawulf.model.powerUp.PowerUpAttack;
import javawulf.model.powerUp.PowerUpFactory;
import javawulf.model.powerUp.PowerUpFactoryImpl;


public class PowerUpTest {
    
    private static final int X_COORDINATE_PLAYER = 10;
    private static final int Y_COORDINATE_PLAYER = 10;
    private static final int X_COORDINATE_POWERUP = 34;
    private static final int Y_COORDINATE_POWERUP = 10;
    private static final int HEALTH = 3;
    private static final int POINTS = 0;

    private Player player;
    private Coordinate coordinatesPowerUp;
    private PowerUpFactory powerUpFactory = new PowerUpFactoryImpl();

    @BeforeEach
    void setUpPlayerAndCoordinates() {
        this.player = new PlayerImpl(X_COORDINATE_PLAYER, Y_COORDINATE_PLAYER, HEALTH, POINTS);
        this.coordinatesPowerUp = new CoordinateImpl(X_COORDINATE_POWERUP, Y_COORDINATE_POWERUP);
    }

    @Test
    void testCollectionPowerUp() {
        // create a powerup near player 
        PowerUpAttack powerUpAttack = powerUpFactory.createPowerUpAttack(coordinatesPowerUp);
        // test if the power up doesnt get pick up if its far away
        assertFalse(player.getBounds().isCollidingWith(powerUpAttack.getBounds().getCollisionArea()));
        Direction movementDirection = Direction.RIGHT;
        player.move(movementDirection);
        // test if now power up is colliding with player
        assertTrue(player.getBounds().isCollidingWith(powerUpAttack.getBounds().getCollisionArea()));
        player.getPowerUpHandler().collectPowerUp(powerUpAttack);

        // test wich powerup is active in now
        assertEquals(player.getPowerUpHandler().getPowerUpActive().get(), powerUpAttack);
    }

    @Test
    void testStatsPowerUpAttack() {
        
    }

}