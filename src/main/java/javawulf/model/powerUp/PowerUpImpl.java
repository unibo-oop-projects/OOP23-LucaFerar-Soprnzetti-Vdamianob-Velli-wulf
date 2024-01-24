package javawulf.model.powerUp;

public abstract class PowerUpImpl implements PowerUp {

    
    private final int durationInMilli;
    private final String type;
    private final int pointsGiven;
    private long activationTimeInMilli;
    
    public PowerUpImpl(int duration, String type, int pointsGiven) {
        this.durationInMilli = duration;
        this.type = type;
        this.pointsGiven = pointsGiven;
    }    
    
    @Override
    public void activateEffect() {
        activationTimeInMilli = System.currentTimeMillis();
    }
    
    public boolean stillActive() {
        return activationTimeInMilli + durationInMilli < System.currentTimeMillis();
    }
    
    @Override
    public String toString() {
        return "PowerUp: [durationInMilli=" + durationInMilli + ", type=" + type + ", pointsGiven=" + pointsGiven + "]";
    }
    
}
