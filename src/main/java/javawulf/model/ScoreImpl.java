package javawulf.model;

public class ScoreImpl implements Score{

    private int points;
    private Multiplier multiplier;

    /**
     * Creates the score for the current game.
     * If a certain amount has been specified it will be used as a strating point
     * for the point total (could be used as a bonus for choosing a harder difficulty)
     */
    public ScoreImpl(int points){
        this.points = points;
        this.multiplier = Multiplier.DEFAULT;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public int getMultiplier() {
        return this.multiplier.getValue();
    }

    @Override
    public void setMultiplier(Multiplier multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void addPoints(int points) {
        this.points = this.points + points * this.getMultiplier();
    }
    
}
