package javawulf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.player.*;
import javawulf.model.player.Score.Multiplier;

public class ScoreTest {
    
    private int health = 3;
    private int startingX = 12;
    private int startingY = 12;
    private int startingPoints = 0;
    private Player player;
    private Score score;
    private int increase = 100;

    @BeforeEach
    void createScore(){
        this.player = new PlayerImpl(startingX, startingY, health, startingPoints);
        this.score = this.player.getScore();
    }

    @Test
    void testStartingScore(){
        assertEquals(startingPoints, this.score.getPoints());
        assertEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
    }

    @Test
    void testIncrease(){
        score.addPoints(increase);
        assertNotEquals(startingPoints, this.score.getPoints());
        assertEquals(increase, this.score.getPoints());
    }

    @Test
    void testIncreaseWithMultiplier(){
        score.setMultiplier(Multiplier.DOUBLE);
        score.addPoints(increase);
        assertNotEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
        assertNotEquals(increase, this.score.getPoints());
        assertEquals(increase*2, this.score.getPoints());

        score.setMultiplier(Multiplier.DEFAULT);
        score.addPoints(increase);
        assertEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
        assertNotEquals(increase*2, this.score.getPoints());
        assertEquals(increase*3, this.score.getPoints());
    }
}
