package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.player.Player;
import javawulf.model.player.PlayerHealth;
import javawulf.model.player.PlayerImpl;


public class PowerUpTest {
    
    private final int xCoordinate = 10;
    private final int yCoordinate = 10;
    private final int health = 3;
    private final int points = 0;

    private Player player;
    private PlayerHealth hp;

    @BeforeEach
    void createPlayer() {
        this.player = new PlayerImpl(xCoordinate, yCoordinate, health, points);
        this.hp = this.player.getPlayerHealth();
    }

    @Test
    void testCollectionPowerUp() {

    }

}