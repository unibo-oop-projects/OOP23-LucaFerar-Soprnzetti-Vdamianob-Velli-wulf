package javawulf.scoreboard;

import java.util.List;

/**
 * Scoreboard stores all the results of past games and the scores of the players.
 */
public interface Scoreboard {

    /**
     * Maximum Results in the scoreboard.
     */
    static final int SCOREBOARD_SIZE = 10;

    /**
     * Updates the scoreboard.
     */
    void addNewScore(Result result);
    
    /**
     * saves the scoreboard.
     */
    void saveScoreBoard();

    /**
     * 
     * @return all the scores presents
     */
    List<Result> getAllScores();

}
