package javawulf.model.powerUp;

import javawulf.model.Coordinate;

public abstract class PowerUpImpl implements PowerUp {

    protected int durationInMilli;
    protected String type;
    protected int pointsGiven;
    protected long activationTimeInMilli;
    protected Coordinate coordinates;
    
    public int getPointsGiven() {
        return this.pointsGiven;
    }

    public Coordinate getCoordinates() {
        return this.coordinates;
    }

    @Override
    public void activateEffect() {
        activationTimeInMilli = System.currentTimeMillis();
    }
    
    @Override
    public boolean stillActive() {
        return activationTimeInMilli + durationInMilli < System.currentTimeMillis();
    }
    
    @Override
    public String toString() {
        return "PowerUp: [durationInMilli=" + durationInMilli + ", type=" + type + ", pointsGiven=" + pointsGiven + "]";
    }
    
}
