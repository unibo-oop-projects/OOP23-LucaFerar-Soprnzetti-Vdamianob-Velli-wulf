package javawulf.model.powerUp;

public class PowerUpInvincibility extends PowerUpImpl {
    
    private final int DURATIONMILLI = 10_000;
    private final int POINTS = 100;
    private final String TYPE = "Invincibility";
    
    public PowerUpInvincibility() {
        this.type = TYPE;
        this.durationInMilli = DURATIONMILLI;
        this.pointsGiven = POINTS;
    }
}
