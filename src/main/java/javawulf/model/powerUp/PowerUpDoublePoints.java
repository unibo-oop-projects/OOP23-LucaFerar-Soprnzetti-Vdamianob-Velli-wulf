package javawulf.model.powerUp;

import javawulf.model.PositionOnMap;

public class PowerUpDoublePoints extends PowerUpImpl {

    private final int DURATIONMILLI = 30_000;
    private final int POINTS = 100;
    private final String TYPE = "DoublePoints";
    
    public PowerUpDoublePoints(PositionOnMap position) {
        this.coordinates = position;
        this.type = TYPE;
        this.durationInMilli = DURATIONMILLI;
        this.pointsGiven = POINTS;
    }
}
