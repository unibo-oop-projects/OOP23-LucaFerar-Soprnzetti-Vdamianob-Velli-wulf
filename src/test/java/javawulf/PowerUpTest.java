package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerHealth;
import javawulf.model.player.PlayerImpl;
import javawulf.model.player.Score;
import javawulf.model.player.SwordImpl;
import javawulf.model.powerUp.PowerUpAttack;
import javawulf.model.powerUp.PowerUpDoublePoints;
import javawulf.model.powerUp.PowerUpFactory;
import javawulf.model.powerUp.PowerUpFactoryImpl;
import javawulf.model.powerUp.PowerUpInvincibility;
import javawulf.model.powerUp.PowerUpSpeed;


public class PowerUpTest {
    
    // SetUp
    private static final int X_COORDINATE_PLAYER = 10;
    private static final int Y_COORDINATE_PLAYER = 10;
    private static final int X_COORDINATE_POWERUP = 34;
    private static final int Y_COORDINATE_POWERUP = 10;
    private static final int HEALTH = 3;
    private static final int POINTS = 0;

    // Results
    private static final int X_COORDINATE_PLAYER_SPEED = 16;
    private static final int Y_COORDINATE_PLAYER_SPEED = 10;

    private Player player;
    private Coordinate coordinatesPowerUp;
    private Coordinate coordinatesPlayer;
    private Coordinate coordinatesPlayerSpeed;
    private PowerUpFactory powerUpFactory = new PowerUpFactoryImpl();

    @BeforeEach
    void setUpPlayerAndCoordinates() {
        this.player = new PlayerImpl(X_COORDINATE_PLAYER, Y_COORDINATE_PLAYER, HEALTH, POINTS);
        this.coordinatesPlayer = new CoordinateImpl(X_COORDINATE_PLAYER, Y_COORDINATE_PLAYER);
        this.coordinatesPlayerSpeed = new CoordinateImpl(X_COORDINATE_PLAYER_SPEED, Y_COORDINATE_PLAYER_SPEED);
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
        //create a powerUp colliding with player
        PowerUpAttack powerUpAttack = powerUpFactory.createPowerUpAttack(coordinatesPlayer);
        player.getPowerUpHandler().collectPowerUp(powerUpAttack);
        //test powerUp activation
        assertTrue(player.getPowerUpHandler().getPowerUpActive().get().stillActive());
        player.getPowerUpHandler().update(player);
        //test powerUp stats changing
        assertEquals(player.getSword().getSwordStrength(), SwordImpl.STRONG);
        assertEquals(player.getPowerUpHandler().getPowerUpActive().get().getDuration(), powerUpAttack.getDuration()); 
        // Test score
        assertTrue(player.getScore().getPoints() != 0);
    }

    @Test
    void testStatsPowerUpDoublePoints() {
        //create a powerUp colliding with player
        PowerUpDoublePoints powerUpDoublePoints = powerUpFactory.createPowerUpDoublePoints(coordinatesPlayer);
        player.getPowerUpHandler().collectPowerUp(powerUpDoublePoints);
        //test powerUp activation
        assertTrue(player.getPowerUpHandler().getPowerUpActive().get().stillActive());
        player.getPowerUpHandler().update(player);
        //test powerUp stats changing
        assertEquals(player.getScore().getMultiplier(), Score.Multiplier.DOUBLE.getValue());
        assertEquals(player.getPowerUpHandler().getPowerUpActive().get().getDuration(), powerUpDoublePoints.getDuration()); 
        // Test score
        assertTrue(player.getScore().getPoints() != 0);
    }

    @Test
    void testStatsPowerUpInvincibility() {
        //create a powerUp colliding with player
        PowerUpInvincibility powerUpInvincibility = powerUpFactory.createPowerUpInvincibility(coordinatesPlayer);
        player.getPowerUpHandler().collectPowerUp(powerUpInvincibility);
        //test powerUp activation
        assertTrue(player.getPowerUpHandler().getPowerUpActive().get().stillActive());
        player.getPowerUpHandler().update(player);
        //test powerUp stats changing
        assertEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());
        assertEquals(player.getPowerUpHandler().getPowerUpActive().get().getDuration(), powerUpInvincibility.getDuration()); 
        // Test score
        assertTrue(player.getScore().getPoints() != 0);
    }

    @Test
    void testStatsPowerUpSpeed() {
        // Create a powerUp colliding with player
        PowerUpSpeed powerUpSpeed = powerUpFactory.createPowerUpSpeed(coordinatesPlayer);
        player.getPowerUpHandler().collectPowerUp(powerUpSpeed);
        // Test powerUp activation
        assertTrue(player.getPowerUpHandler().getPowerUpActive().get().stillActive());
        player.getPowerUpHandler().update(player);
        // Test powerUp stats changing
        Direction movementDirection = Direction.RIGHT;
        player.move(movementDirection);
        assertEquals(player.getPosition().getX(), coordinatesPlayerSpeed.getX());
        assertEquals(player.getPowerUpHandler().getPowerUpActive().get().getDuration(), powerUpSpeed.getDuration()); 
        // Test score
        assertTrue(player.getScore().getPoints() != 0);
    }
}