package javawulf.model.powerUp;

public class PowerUpAttack extends PowerUpImpl {
    
    private final int DURATIONMILLI = 20_000;
    private final int POINTS = 50;
    private final String TYPE = "Attack";
    
    public PowerUpAttack() {
        this.type = TYPE;
        this.durationInMilli = DURATIONMILLI;
        this.pointsGiven = POINTS;
    }
}
