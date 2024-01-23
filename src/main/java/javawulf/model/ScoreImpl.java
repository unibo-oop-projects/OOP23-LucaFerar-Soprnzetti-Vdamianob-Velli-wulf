package javawulf.model;

public class ScoreImpl implements Score{

    private int points;
    private Multiplier multiplier;

    public ScoreImpl(){
        this.points = 0;
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
