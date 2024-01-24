package javawulf.model.powerUp;

import javawulf.model.PositionOnMap;

public class PowerUpSpeed extends PowerUpImpl{
    
    private final int DURATIONMILLI = 30_000;
    private final int POINTS = 50;
    private final String TYPE = "Speed";

    public PowerUpSpeed(PositionOnMap position) {
        this.coordinates = position;
        this.type = TYPE;
        this.durationInMilli = DURATIONMILLI;
        this.pointsGiven = POINTS;
    }
}
