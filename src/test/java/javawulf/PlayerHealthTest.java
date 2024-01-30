package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.BoundingBox;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.AbstractEntity;
import javawulf.model.player.*;
import javawulf.model.player.PlayerHealth.ShieldStatus;

public final class PlayerHealthTest {

    private int health = 3;
    private int startingX = 12;
    private int startingY = 12;
    private int startingPoints = 0;
    private Player player;
    private PlayerHealth hp;

    @BeforeEach
    void createPlayer() {
        this.player = new PlayerImpl(startingX, startingY, health, startingPoints);
        this.hp = this.player.getPlayerHealth();
    }

    @Test
    void testStartingPlayerHealth() {
        assertEquals(health, this.hp.getHealth());
        assertEquals(health, this.hp.getMaxHealth());
        assertEquals(ShieldStatus.NONE, this.hp.getShieldStatus());
    }

    @Test
    void testDamageComingFromPlayer() {
        BoundingBox enemy = new BoundingBoxImpl(startingX, startingY,
            AbstractEntity.OBJECT_SIZE, AbstractEntity.OBJECT_SIZE, CollisionType.ENEMY);
        this.player.isHit(enemy);
        assertEquals(health - 1, this.hp.getHealth());
        assertEquals(health, this.hp.getMaxHealth());
    }

    @Test
    void testHealthChange() {
        this.hp.setHealth(-1);
        assertEquals(health - 1, this.hp.getHealth());
        assertEquals(health, this.hp.getMaxHealth());
        this.hp.setHealth(2);
        assertEquals(health, this.hp.getHealth());
        assertEquals(health, this.hp.getMaxHealth());
    }

    @Test
    void testMaxHealthIncrease() {
        this.hp.increaseMaxHealth(1);
        assertEquals(health + 1, this.hp.getMaxHealth());
        assertEquals(health, this.hp.getHealth());
    }

    @Test
    void testShield() {
        this.hp.setShieldStatus(ShieldStatus.FULL);
        assertEquals(ShieldStatus.FULL, this.hp.getShieldStatus());

        this.hp.setHealth(-1);
        assertEquals(health, this.hp.getHealth());
        assertEquals(ShieldStatus.HALF, this.hp.getShieldStatus());

        this.hp.setHealth(-1);
        assertEquals(health, this.hp.getHealth());
        assertEquals(ShieldStatus.NONE, this.hp.getShieldStatus());

        this.hp.setHealth(-1);
        assertEquals(health - 1, this.hp.getHealth());
        assertEquals(ShieldStatus.NONE, this.hp.getShieldStatus());
    }

    @Test
    void testHealthChangeAndShield() {
        this.hp.setHealth(-1);
        this.hp.setShieldStatus(ShieldStatus.FULL);
        this.hp.setHealth(-1);
        assertEquals(health - 1, this.hp.getHealth());
        assertEquals(ShieldStatus.HALF, this.hp.getShieldStatus());
    }

}
