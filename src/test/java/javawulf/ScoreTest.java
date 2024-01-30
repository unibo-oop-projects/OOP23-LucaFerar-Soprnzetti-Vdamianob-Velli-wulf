package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;
import javawulf.model.player.Score;
import javawulf.model.player.Score.Multiplier;

/**
 * ScoreTest checks whether the implementation of Score increases
 * and decreases the point total as it should, considering the
 * current mulitplier.
 */
public final class ScoreTest {

    private final int health = 3;
    private final int startingX = 12;
    private final int startingY = 12;
    private final int startingPoints = 0;
    private Player player;
    private Score score;
    private final int increase = 100;

    @BeforeEach
    void createScore() {
        this.player = new PlayerImpl(startingX, startingY, health, startingPoints);
        this.score = this.player.getScore();
    }

    @Test
    void testStartingScore() {
        assertEquals(startingPoints, this.score.getPoints());
        assertEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
    }

    @Test
    void testIncrease() {
        score.addPoints(increase);
        assertNotEquals(startingPoints, this.score.getPoints());
        assertEquals(increase, this.score.getPoints());
    }

    @Test
    void testIncreaseWithMultiplier() {
        score.setMultiplier(Multiplier.DOUBLE);
        score.addPoints(increase);
        assertNotEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
        assertNotEquals(increase, this.score.getPoints());
        assertEquals(increase * 2, this.score.getPoints());

        score.setMultiplier(Multiplier.DEFAULT);
        score.addPoints(increase);
        assertEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
        assertNotEquals(increase * 2, this.score.getPoints());
        assertEquals(increase * 3, this.score.getPoints());
    }
}
