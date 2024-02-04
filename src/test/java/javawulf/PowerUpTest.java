package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.map.Map;
import javawulf.model.map.factory.MapFactoryImpl;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;
import javawulf.model.player.Score;
import javawulf.model.player.Sword;
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
    private Map map;

    @BeforeEach
    void setUpPlayerAndCoordinates() {
        this.player = new PlayerImpl(X_COORDINATE_PLAYER, Y_COORDINATE_PLAYER, HEALTH, POINTS);
        this.coordinatesPlayer = new CoordinateImpl(X_COORDINATE_PLAYER, Y_COORDINATE_PLAYER);
        this.coordinatesPlayerSpeed = new CoordinateImpl(X_COORDINATE_PLAYER_SPEED, Y_COORDINATE_PLAYER_SPEED);
        this.coordinatesPowerUp = new CoordinateImpl(X_COORDINATE_POWERUP, Y_COORDINATE_POWERUP);
        this.map = new MapFactoryImpl().getTestMap(player);
    }

    @Test
    void testCollectionPowerUp() {
        // Create a powerup near player 
        PowerUpAttack powerUpAttack = powerUpFactory.createPowerUpAttack(coordinatesPowerUp);
        // Test if the power up doesnt get pick up if its far away
        assertFalse(player.getBounds().isCollidingWith(powerUpAttack.getBounds().getCollisionArea()));
        Direction movementDirection = Direction.RIGHT;
        player.move(movementDirection, map);
        // Test if now power up is colliding with player
        assertTrue(player.getBounds().isCollidingWith(powerUpAttack.getBounds().getCollisionArea()));
        player.getPowerUpHandler().collectPowerUp(powerUpAttack, player);
        powerUpAttack.collect(player);
        // Test wich powerup is active in now
        assertEquals(powerUpAttack, player.getPowerUpHandler().getPowerUpActive().get());
    }

    @Test
    void testCollectionAndSubstitutionPowerUp(){
        PowerUpAttack powerUpAttack = powerUpFactory.createPowerUpAttack(coordinatesPlayer);
        player.getPowerUpHandler().collectPowerUp(powerUpAttack, player);
        powerUpAttack.collect(player);
        // Test if powerUp active is attack
        assertEquals(powerUpAttack.getType(), player.getPowerUpHandler().getPowerUpActive().get().getType());
        assertEquals(SwordImpl.STRONG, player.getSword().getSwordStrength());
        assertEquals(powerUpAttack.getDuration(), player.getPowerUpHandler().getPowerUpActive().get().getDuration()); 
        // Change powerUp
        PowerUpDoublePoints powerUpDoublePoints = powerUpFactory.createPowerUpDoublePoints(coordinatesPlayer);
        player.getPowerUpHandler().collectPowerUp(powerUpDoublePoints, player);
        assertEquals(powerUpDoublePoints.getType(), player.getPowerUpHandler().getPowerUpActive().get().getType());
        assertEquals(Score.Multiplier.DOUBLE.getValue(), player.getScore().getMultiplier());
        

    }

    @Test
    void testStatsPowerUpAttack() {
        // Create a powerUp colliding with player
        PowerUpAttack powerUpAttack = powerUpFactory.createPowerUpAttack(coordinatesPlayer);
        player.getPowerUpHandler().collectPowerUp(powerUpAttack, player);
        powerUpAttack.collect(player);
        // Test powerUp activation
        assertTrue(player.getPowerUpHandler().getPowerUpActive().get().stillActive());
        player.getPowerUpHandler().update(player);
        // Test powerUp stats changing
        assertEquals(SwordImpl.STRONG, player.getSword().getSwordStrength());
        assertEquals(powerUpAttack.getDuration(), player.getPowerUpHandler().getPowerUpActive().get().getDuration()); 
        // Test score
        assertTrue(player.getScore().getPoints() != 0);
    }

    @Test
    void testStatsPowerUpDoublePoints() {
        // Create a powerUp colliding with player
        PowerUpDoublePoints powerUpDoublePoints = powerUpFactory.createPowerUpDoublePoints(coordinatesPlayer);
        player.getPowerUpHandler().collectPowerUp(powerUpDoublePoints, player);
        powerUpDoublePoints.collect(player);
        // Test powerUp activation
        assertTrue(player.getPowerUpHandler().getPowerUpActive().get().stillActive());
        player.getPowerUpHandler().update(player);
        // Test powerUp stats changing
        assertEquals(Score.Multiplier.DOUBLE.getValue(), player.getScore().getMultiplier());
        assertEquals(powerUpDoublePoints.getDuration(), player.getPowerUpHandler().getPowerUpActive().get().getDuration()); 
        // Test score
        assertTrue(player.getScore().getPoints() != 0);
    }

    @Test
    void testStatsPowerUpInvincibility() {
        // Create a powerUp colliding with player
        PowerUpInvincibility powerUpInvincibility = powerUpFactory.createPowerUpInvincibility(coordinatesPlayer);
        player.getPowerUpHandler().collectPowerUp(powerUpInvincibility, player);
        powerUpInvincibility.collect(player);
        // Test powerUp activation
        assertTrue(player.getPowerUpHandler().getPowerUpActive().get().stillActive());
        player.getPowerUpHandler().update(player);
        // Test powerUp stats changing
        assertEquals(CollisionType.STUNNED, player.getBounds().getCollisionType());
        assertEquals(powerUpInvincibility.getDuration(), player.getPowerUpHandler().getPowerUpActive().get().getDuration()); 
        // Test score
        assertTrue(player.getScore().getPoints() != 0);
    }

    @Test
    void testStatsPowerUpSpeed() {
        // Create a powerUp colliding with player
        PowerUpSpeed powerUpSpeed = powerUpFactory.createPowerUpSpeed(coordinatesPlayer);
        player.getPowerUpHandler().collectPowerUp(powerUpSpeed, player);
        powerUpSpeed.collect(player);
        // Test powerUp activation
        assertTrue(player.getPowerUpHandler().getPowerUpActive().get().stillActive());
        player.getPowerUpHandler().update(player);
        // Test powerUp stats changing
        Direction movementDirection = Direction.RIGHT;
        player.move(movementDirection, map);
        assertEquals(coordinatesPlayerSpeed.getX(), player.getPosition().getX());
        assertEquals(powerUpSpeed.getDuration(), player.getPowerUpHandler().getPowerUpActive().get().getDuration()); 
        // Test score
        assertTrue(player.getScore().getPoints() != 0);
    }

    @Test
    void testPowerUpDuration() {
        // Create a powerUp colliding with player
        PowerUpAttack powerUpAttack = powerUpFactory.createPowerUpAttack(coordinatesPlayer);
        // powerUp gets collected and activated
        player.getPowerUpHandler().collectPowerUp(powerUpAttack, player);
        powerUpAttack.collect(player);
        // Test if powerUp got collected and updated player stats
        assertEquals(powerUpAttack.getDuration(), 19);
        assertEquals(Player.PlayerColor.STRENGTH.getColor(), player.getColor());
        assertEquals(Sword.STRONG, player.getSword().getSwordStrength());
        // Let the powerUp deactivate
        for (int i = 0; i < 18; i++) {
            player.getPowerUpHandler().update(player);
            assertEquals(Player.PlayerColor.STRENGTH.getColor(), player.getColor());
            assertEquals(Sword.STRONG, player.getSword().getSwordStrength());
        }
        // deactivate powerUp
        player.getPowerUpHandler().update(player);
        assertEquals(Player.PlayerColor.NONE.getColor(), player.getColor());
        assertEquals(Sword.NORMAL, player.getSword().getSwordStrength());
    }

}