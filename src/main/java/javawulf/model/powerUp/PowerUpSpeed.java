package javawulf.model.powerUp;

public class PowerUpSpeed extends PowerUpImpl{
    
    private final int DURATIONMILLI = 30_000;
    private final int POINTS = 50;
    private final String TYPE = "Speed";

    public PowerUpSpeed() {
        this.type = TYPE;
        this.durationInMilli = DURATIONMILLI;
        this.pointsGiven = POINTS;
    }
}
