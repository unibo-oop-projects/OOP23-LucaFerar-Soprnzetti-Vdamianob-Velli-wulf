package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.scoreboard.ResultImpl;
import javawulf.scoreboard.ScoreBoardImpl;
import javawulf.scoreboard.Scoreboard;

final class ScoreBoardTest {

    private Scoreboard scoreboard;

    @BeforeEach
    void populateScoreBoard() {
        scoreboard = new ScoreBoardImpl();
    }

    @Test
    void testAddNewScore() {
        scoreboard.addNewScore(new ResultImpl("marco", 1130, false));
        assertEquals(1, scoreboard.getAllScores().size());
        assertEquals(1130, scoreboard.getAllScores().get(0).getScore());
    }

    @Test 
    void testSortScoreboard() {
        scoreboard.addNewScore(new ResultImpl("giovanni", 1200, false));
        scoreboard.addNewScore(new ResultImpl("giacomo", 1300, true));
        scoreboard.addNewScore(new ResultImpl("aldo", 1100, false));
        scoreboard.addNewScore(new ResultImpl("shrek", 100, false));

        assertEquals("giacomo", scoreboard.getAllScores().get(0).getUserName());
        assertEquals("giovanni", scoreboard.getAllScores().get(1).getUserName());

        scoreboard.saveScoreBoard();
    }
}
