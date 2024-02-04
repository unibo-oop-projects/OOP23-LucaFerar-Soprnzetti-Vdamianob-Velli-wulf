package javawulf.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardImpl implements Scoreboard{

    private List<Result> scoreboard;

    public ScoreBoardImpl() {
        this.scoreboard = new ArrayList<>();
    }

    @Override
    public void addNewScore(Result result) {
        this.scoreboard.add(result);
        orderScoreBoard();
        if(scoreboard.size() > SCOREBOARD_SIZE){
            this.scoreboard.remove(SCOREBOARD_SIZE);
        }
    }

    @Override
    public void saveScoreBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveScoreBoard'");
    }

    @Override
    public List<Result> getAllScores() {
        return this.scoreboard;
    }

    private void orderScoreBoard() {
        scoreboard = scoreboard.stream()
            .sorted(Comparator.comparingInt(Result::getScore))
            .collect(Collectors.toList());
    }
    
}
