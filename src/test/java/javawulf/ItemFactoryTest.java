package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.GameObject;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.item.AmuletPiece;
import javawulf.model.item.Cure;
import javawulf.model.item.CureMax;
import javawulf.model.item.ExtraHeart;
import javawulf.model.item.GreatSword;
import javawulf.model.item.ItemFactory;
import javawulf.model.item.ItemFactoryImpl;
import javawulf.model.item.Shield;

public class ItemFactoryTest {

    int startingX = 12;
    int startingY = 12;

    Coordinate position = new CoordinateImpl(startingX, startingY);
    ItemFactory factory;

    @BeforeEach
    void setUpFactory() {
        factory = new ItemFactoryImpl();
    }

    @Test
    void testCreateAmuletPiece() {
        AmuletPiece piece = factory.createAmuletPiece(position);
        // Check if the piece is correctly created
        assertNotNull(piece);
        // Check if the piece is instantiated as an AmuletPiece
        assertTrue(piece instanceof AmuletPiece);
        // Check the coordinates of the piece
        assertEquals(position.getPosition(), piece.getPosition().getPosition());
        assertEquals(startingX, piece.getPosition().getX());
        assertEquals(startingY, piece.getPosition().getY());
        // Check the BoundingBox of the piece
        assertEquals(piece.getBounds(), new BoundingBoxImpl(startingX, startingY, GameObject.OBJECT_SIZE,
                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE));
    }

    @Test
    void testCreateCure() {
        Cure cure = factory.createCure(position);
        // Check if the cure is correctly created
        assertNotNull(cure);
        // Check if the cure is instantiated as a Cure
        assertTrue(cure instanceof Cure);
        // Check the coordinates of the cure
        assertEquals(position.getPosition(), cure.getPosition().getPosition());
        assertEquals(startingX, cure.getPosition().getX());
        assertEquals(startingY, cure.getPosition().getY());
        // Check the BoundingBox of the cure
        assertEquals(cure.getBounds(), new BoundingBoxImpl(startingX, startingY, GameObject.OBJECT_SIZE,
                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE));
    }

    @Test
    void testCreateCureMax() {
        CureMax cureMax = factory.createCureMax(position);
        // Check if the cureMax is correctly created
        assertNotNull(cureMax);
        // Check if the cureMax is instantiated as a CureMax
        assertTrue(cureMax instanceof CureMax);
        // Check the coordinates of the cureMax
        assertEquals(position.getPosition(), cureMax.getPosition().getPosition());
        assertEquals(startingX, cureMax.getPosition().getX());
        assertEquals(startingY, cureMax.getPosition().getY());
        // Check the BoundingBox of the cureMax
        assertEquals(cureMax.getBounds(), new BoundingBoxImpl(startingX, startingY, GameObject.OBJECT_SIZE,
                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE));
    }

    @Test
    void testCreateExtraHeart() {
        ExtraHeart heart = factory.createExtraHeart(position);
        // Check if the heart is correctly created
        assertNotNull(heart);
        // Check if the heart is instantiated as an ExtraHeart
        assertTrue(heart instanceof ExtraHeart);
        // Check the coordinates of the heart
        assertEquals(position.getPosition(), heart.getPosition().getPosition());
        assertEquals(startingX, heart.getPosition().getX());
        assertEquals(startingY, heart.getPosition().getY());
        // Check the BoundingBox of the heart
        assertEquals(heart.getBounds(), new BoundingBoxImpl(startingX, startingY, GameObject.OBJECT_SIZE,
                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE));
    }

    @Test
    void testCreateGreatSword() {
        GreatSword greatSword = factory.createGreatSword(position);
        // Check if the greatSword is correctly created
        assertNotNull(greatSword);
        // Check if the greatSword is instantiated as a GreatSword
        assertTrue(greatSword instanceof GreatSword);
        // Check the coordinates of the greatSword
        assertEquals(position.getPosition(), greatSword.getPosition().getPosition());
        assertEquals(startingX, greatSword.getPosition().getX());
        assertEquals(startingY, greatSword.getPosition().getY());
        // Check the BoundingBox of the greatSword
        assertEquals(greatSword.getBounds(), new BoundingBoxImpl(startingX, startingY, GameObject.OBJECT_SIZE,
                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE));
    }

    @Test
    void testCreateMinimap() {
        //TODO: First need to implement the minimap
    }

    @Test
    void testCreateShield() {
        Shield shield = factory.createShield(position);
        // Check if the shield is correctly created
        assertNotNull(shield);
        // Check if the shield is instantiated as a Shield
        assertTrue(shield instanceof Shield);
        // Check the coordinates of the shield
        assertEquals(position.getPosition(), shield.getPosition().getPosition());
        assertEquals(startingX, shield.getPosition().getX());
        assertEquals(startingY, shield.getPosition().getY());
        // Check the BoundingBox of the shield
        assertEquals(shield.getBounds(), new BoundingBoxImpl(startingX, startingY, GameObject.OBJECT_SIZE,
                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE));
    }

}
