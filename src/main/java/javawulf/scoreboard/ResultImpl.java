package javawulf.scoreboard;

public class ResultImpl implements Result {

    private final String username;
    private final int score;
    private final boolean won;

    public ResultImpl(String username, int score, boolean won) {
        this.username = username;
        this.score = score;
        this.won = won;
    }

    @Override
    public String getUserName() {
        return this.username;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public boolean getWon() {
        return this.won;
    }
     
}
