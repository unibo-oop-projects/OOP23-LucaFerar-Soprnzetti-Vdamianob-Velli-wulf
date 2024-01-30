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

    private int health = 3;
    private int startingX = 12;
    private int startingY = 12;
    private int startingPoints = 0;
    private Sword sword;
    private Player player;
    private Coordinate test;
    private Direction startDirection = Direction.DOWN;
    private int delta = GameObject.OBJECT_SIZE;
    private BoundingBox startBox;
    private Coordinate playerPosition;

    @BeforeEach
    void createPlayer(){
        this.test = new CoordinateImpl(startingX + (int)(startDirection.getX()*GameObject.OBJECT_SIZE),
            startingY + (int)(startDirection.getY()*GameObject.OBJECT_SIZE));
        this.player = new PlayerImpl(startingX, startingY, health, startingPoints);
        this.sword = this.player.getSword();
        this.startBox = new BoundingBoxImpl(startingX + (int)(startDirection.getX()*GameObject.OBJECT_SIZE),
            startingY + (int)(startDirection.getY()*GameObject.OBJECT_SIZE), GameObject.OBJECT_SIZE,
            GameObject.OBJECT_SIZE, CollisionType.INACTIVE);
    }

    @Test
    void testStartingSwordStatistics() {
        assertEquals(test.getPosition(), this.sword.getPosition().getPosition());
        assertEquals(startBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(1, this.sword.getSwordStrength());
        assertEquals(SwordType.NORMAL, this.sword.getSwordType());
        assertNotEquals(CollisionType.SWORD, this.sword.getBounds().getCollisionType());
    }

    @Test
    void testSwordMovement() {
        Direction movementDirection = Direction.UP;
        this.player.move(movementDirection);
        playerPosition = this.player.getPosition();
        BoundingBox expectBox = new BoundingBoxImpl(playerPosition.getX() + (int)(movementDirection.getX()*delta),
            playerPosition.getY() + (int)(movementDirection.getY()*delta),  GameObject.OBJECT_SIZE,
            GameObject.OBJECT_SIZE, CollisionType.INACTIVE);
        
        assertFalse(this.sword.getBounds().isCollidingWith(this.player.getBounds().getCollisionArea()));
        assertNotEquals(this.player.getPosition().getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(test.getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(startBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
    }

    @Test
    void testSwordDiagonalMovement() {
        Direction movementDirection = Direction.DOWN_LEFT;
        this.player.move(movementDirection);
        playerPosition = this.player.getPosition();
        Coordinate expectCoordinate = new CoordinateImpl(playerPosition.getX() + (int)(Direction.DOWN.getX()*delta),
            playerPosition.getY() + (int)(Direction.DOWN.getY()*delta));
        BoundingBox expectBox = new BoundingBoxImpl(playerPosition.getX() + (int)(Direction.DOWN.getX()*delta),
            playerPosition.getY() + (int)(Direction.DOWN.getY()*delta),  GameObject.OBJECT_SIZE,
            GameObject.OBJECT_SIZE, CollisionType.INACTIVE);
        
        assertFalse(this.sword.getBounds().isCollidingWith(this.player.getBounds().getCollisionArea()));
        assertNotEquals(test.getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(startBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectCoordinate.getPosition(), this.sword.getPosition().getPosition());
    }

    @Test
    void testSwordOppositeDiagonalMovement() {
        Direction movementDirection = Direction.UP_RIGHT;
        this.player.move(movementDirection);
        playerPosition = this.player.getPosition();
        Coordinate expectCoordinate = new CoordinateImpl(playerPosition.getX() + (int)(Direction.RIGHT.getX()*delta),
            playerPosition.getY() + (int)(Direction.RIGHT.getY()*delta));
        BoundingBox expectBox = new BoundingBoxImpl(playerPosition.getX() + (int)(Direction.RIGHT.getX()*delta),
            playerPosition.getY() + (int)(Direction.RIGHT.getY()*delta),  GameObject.OBJECT_SIZE,
            GameObject.OBJECT_SIZE, CollisionType.INACTIVE);
        
        assertFalse(this.sword.getBounds().isCollidingWith(this.player.getBounds().getCollisionArea()));
        assertNotEquals(test.getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(startBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectCoordinate.getPosition(), this.sword.getPosition().getPosition());
    }

    @Test
    void testActivation(){
        this.sword.activate();
        assertEquals(CollisionType.SWORD, this.sword.getBounds().getCollisionType());

        this.sword.deactivate();
        assertNotEquals(CollisionType.SWORD, this.sword.getBounds().getCollisionType());
    }

    @Test
    void testTypeChange(){
        this.sword.changeSwordType();
        assertEquals(SwordType.GREATSWORD, this.sword.getSwordType());

        this.sword.changeSwordType();
        assertNotEquals(SwordType.GREATSWORD, this.sword.getSwordType());
    }

    @Test
    void testGreatSwordCollisionArea(){
        this.sword.changeSwordType();
        Direction movementDirection = Direction.RIGHT;
        this.player.move(movementDirection);
        playerPosition = this.player.getPosition();
        int constantHeight = 1;
        int constantWidth = 1;
        if(Math.abs((int)movementDirection.getX()) > 0){
            constantHeight = 3;
        } else {
            constantWidth = 3;
        }
        BoundingBox expectBox = new BoundingBoxImpl(playerPosition.getX() + (int)(movementDirection.getX()*delta),
            playerPosition.getY() + (int)(movementDirection.getY()*delta),  GameObject.OBJECT_SIZE*constantWidth,
            GameObject.OBJECT_SIZE*constantHeight, CollisionType.INACTIVE);
        
        assertFalse(this.sword.getBounds().isCollidingWith(this.player.getBounds().getCollisionArea()));
        assertNotEquals(this.player.getPosition().getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(test.getPosition(), this.sword.getPosition().getPosition());
        assertNotEquals(startBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
        assertEquals(expectBox.getCollisionArea(), this.sword.getBounds().getCollisionArea());
    }

}
