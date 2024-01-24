package javawulf.model.powerUp;

public class PowerUpSpeed extends PowerUpImpl{
    
    private final int DURATIONMILLI = 3000;
    private final int POINTS = 50;
    private final String TYPE = "speed";
    
    public PowerUpSpeed() {
        this.type = TYPE;
        this.durationInMilli = DURATIONMILLI;
        this.pointsGiven = POINTS;
    }

}
