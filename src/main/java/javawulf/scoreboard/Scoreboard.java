package javawulf.scoreboard;

import java.util.List;

public interface Scoreboard {

    /**
     * Maximum Results in the scoreboard.
     */
    public static final int SCOREBOARD_SIZE = 10;

    /**
     * Updates the scoreboard.
     */
    public void addNewScore(Result result);
    
    /**
     * saves the scoreboard.
     */
    public void saveScoreBoard();

    /**
     * 
     * @return all the scores presents
     */
    public List<Result> getAllScores();

}
