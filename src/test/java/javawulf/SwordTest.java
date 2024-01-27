package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.BoundingBox;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.GameObject;
import javawulf.model.player.*;
import javawulf.model.player.Sword.SwordType;

public class SwordTest {

    int health = 3;
    int startingX = 12;
    int startingY = 12;
    int startingPoints = 0;
    Sword sword;
    Coordinate test;
    Direction startDirection = Direction.DOWN;

    @BeforeEach
    void createPlayer(){
        this.test = new CoordinateImpl(startingX, startingY);
        this.sword = new SwordImpl(test, startDirection);
    }

    @Test
    void testStartingSwordStatistics() {
        BoundingBox expectBox = new BoundingBoxImpl(startingX + (int)(startDirection.getX()*GameObject.OBJECT_SIZE),
            startingY + (int)(startDirection.getY()*GameObject.OBJECT_SIZE), GameObject.OBJECT_SIZE,
            GameObject.OBJECT_SIZE, CollisionType.STUNNED);
        assertEquals(test, this.sword.getPosition());
        assertEquals(expectBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(1, this.sword.getSwordStrength());
        assertEquals(SwordType.NORMAL, this.sword.getSwordType());
        assertNotEquals(CollisionType.SWORD, this.sword.getBounds().getCollisionType());
    }

    @Test
    void testSwordMovement() {
        //Direction movementDirection = Direction.DOWN_LEFT;
        //int delta = Entity.MOVEMENT_DELTA;
    }

}
