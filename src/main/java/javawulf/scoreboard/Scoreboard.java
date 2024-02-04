package javawulf.scoreboard;

import java.io.File;
import java.util.List;

/**
 * Scoreboard stores all the results of past games and the scores of the players.
 */
public interface Scoreboard {

    /**
     * Maximum Results in the scoreboard.
     */
    int SCOREBOARD_SIZE = 10;

    /**
     * 
     */
    String FILE_PATH = new File(".."+ File.separator + "resources" + File.separator +"Scoreboard.txt").getAbsolutePath();

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
