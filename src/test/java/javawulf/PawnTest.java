package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.Direction;
import javawulf.model.GameObject;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.enemy.EnemyFactory;
import javawulf.model.enemy.EnemyFactoryImpl;
import javawulf.model.enemy.Pawn;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;

public class PawnTest {

    Integer speed = 1;
    int points = 100;
    int startingX = 12;
    int startingY = 12;

    EnemyFactory factory = new EnemyFactoryImpl();
    Player player = new PlayerImpl(0, 0, 3, 0);
    Coordinate position = new CoordinateImpl(startingX, startingY);
    Pawn pawn;

    @BeforeEach
    void createPawn() {
        pawn = factory.createPawn(position);
    }

    @Test
    void testPawnStatistics() {
        assertEquals(position.getPosition(), pawn.getPosition().getPosition());
        assertEquals(new BoundingBoxImpl(startingX, startingY, GameObject.OBJECT_SIZE, GameObject.OBJECT_SIZE,
                CollisionType.ENEMY).getCollisionArea(), pawn.getBounds().getCollisionArea());
        assertEquals(new BoundingBoxImpl(startingX, startingY, GameObject.OBJECT_SIZE, GameObject.OBJECT_SIZE,
                CollisionType.ENEMY).getCollisionType(), pawn.getBounds().getCollisionType());
        assertEquals(speed, pawn.getSpeed());
        assertTrue(pawn.isAlive());
        assertNotNull(pawn.getDirection());
    }

    @Test
    void testMove() throws InterruptedException {
        Direction originalDirection = pawn.getDirection();
        for (int i = 0; i < 100; i++){
            Thread.sleep((pawn.getTimeToWait() + 1) * 1000);
            pawn.move(this.player);
            if (pawn.getDirection() != originalDirection){
                return;
            }
        }
        fail("Pawn did not change direction after 100 seconds");
    }

}
