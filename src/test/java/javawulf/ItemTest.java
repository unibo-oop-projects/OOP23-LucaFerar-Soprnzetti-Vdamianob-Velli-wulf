package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.item.AmuletPiece;
import javawulf.model.item.Cure;
import javawulf.model.item.CureMax;
import javawulf.model.item.ExtraHeart;
import javawulf.model.item.GreatSword;
import javawulf.model.item.ItemFactory;
import javawulf.model.item.ItemFactoryImpl;
import javawulf.model.item.Shield;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;
import javawulf.model.player.PlayerHealth.ShieldStatus;
import javawulf.model.player.Sword.SwordType;

public class ItemTest {
    
    int startingX = 12;
    int startingY = 12;
    int startingHealth = 3;

    ItemFactory factory = new ItemFactoryImpl();
    Coordinate test;
    Player player;

    @BeforeEach
    void setUp(){
        this.player = new PlayerImpl(startingX, startingX, startingHealth, 0);
        this.test = new CoordinateImpl(startingX, startingY);
    }

    @Test
    void testCollection(){
        // Creating an item (any item will do for this test)
        Shield shield = factory.createShield(test);
        // Checking that the player meets the requirements to collect the item
        assertTrue(player.getBounds().isCollidingWith(shield.getBounds().getCollisionArea()));
        // Collecting the item
        shield.collect(player);
        // Checking that the item has been collected
        assertNotEquals(0, player.getScore().getPoints());
    }

    @Test
    void testAmuletPiece(){
        AmuletPiece piece = factory.createAmuletPiece(test);
        // Player collect the item
        piece.collect(player);
        // Checking that the effect has been applied
        assertTrue(player.getPieces().contains(piece));
    }

    @Test
    void testCure(){
        Cure cure = factory.createCure(test);
        player.getPlayerHealth().setHealth(-2);
        // Player collect the item
        cure.collect(player);
        // Checking that the effect has been applied
        assertEquals(2, player.getPlayerHealth().getHealth());
    }

    @Test
    void testCureMax(){
        CureMax cure = factory.createCureMax(test);
        player.getPlayerHealth().setHealth(-2);
        // Player collect the item
        cure.collect(player);
        // Checking that the effect has been applied
        assertEquals(player.getPlayerHealth().getHealth(), player.getPlayerHealth().getMaxHealth());
    }

    @Test
    void testExtraHeart(){
        ExtraHeart heart = factory.createExtraHeart(test);
        // Player collect the item
        heart.collect(player);
        // Checking that the effect has been applied correctly
        assertEquals(startingHealth + 1, player.getPlayerHealth().getMaxHealth());
        assertNotEquals(player.getPlayerHealth().getMaxHealth(), player.getPlayerHealth().getHealth());
    }

    @Test
    void testGreatSword(){
        GreatSword greatSword = factory.createGreatSword(test);
        // Player collect the item
        greatSword.collect(player);
        // Checking that the effect has been applied correctly
        assertEquals( SwordType.GREATSWORD, player.getSword().getSwordType() );
        assertEquals(2,player.getSword().getSwordStrength());
    }

    @Test
    void testMinimap(){
        //TODO: Implement minimap
    }

    @Test
    void testShield(){
        Shield shield = factory.createShield(test);
        // Player collect the item
        shield.collect(player);
        // Checking that the effect has been applied correctly
        assertEquals(ShieldStatus.FULL, player.getPlayerHealth().getShieldStatus());
    }
}
