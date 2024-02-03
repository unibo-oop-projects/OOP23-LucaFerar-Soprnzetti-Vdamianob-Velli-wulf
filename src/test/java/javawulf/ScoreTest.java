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
final class ScoreTest {

    private static final int HEALTH = 3;
    private static final int STARTING_X = 12;
    private static final int STARTING_Y = 12;
    private static final int STARTING_POINTS = 0;
    private Score score;
    private static final int INCREASE = 100;

    @BeforeEach
    void createScore() {
        final Player player = new PlayerImpl(STARTING_X, STARTING_Y, HEALTH, STARTING_POINTS);
        this.score = player.getScore();
    }

    @Test
    void testStartingScore() {
        assertEquals(STARTING_POINTS, this.score.getPoints());
        assertEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
    }

    @Test
    void testIncrease() {
        score.addPoints(INCREASE);
        assertNotEquals(STARTING_POINTS, this.score.getPoints());
        assertEquals(INCREASE, this.score.getPoints());
    }

    @Test
    void testIncreaseWithMultiplier() {
        score.setMultiplier(Multiplier.DOUBLE);
        score.addPoints(INCREASE);
        assertNotEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
        assertNotEquals(INCREASE, this.score.getPoints());
        assertEquals(INCREASE * 2, this.score.getPoints());

        score.setMultiplier(Multiplier.DEFAULT);
        score.addPoints(INCREASE);
        assertEquals(Multiplier.DEFAULT.getValue(), this.score.getMultiplier());
        assertNotEquals(INCREASE * 2, this.score.getPoints());
        assertEquals(INCREASE * 3, this.score.getPoints());
    }
}
