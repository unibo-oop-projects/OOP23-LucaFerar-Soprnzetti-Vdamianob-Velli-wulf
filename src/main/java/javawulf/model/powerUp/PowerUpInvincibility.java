package javawulf.model.powerUp;

import javawulf.model.PositionOnMap;

public class PowerUpInvincibility extends PowerUpImpl {
    
    private final int DURATIONMILLI = 10_000;
    private final int POINTS = 100;
    private final String TYPE = "Invincibility";
    
    public PowerUpInvincibility(PositionOnMap position) {
        this.coordinates = position;
        this.type = TYPE;
        this.durationInMilli = DURATIONMILLI;
        this.pointsGiven = POINTS;
    }
}
