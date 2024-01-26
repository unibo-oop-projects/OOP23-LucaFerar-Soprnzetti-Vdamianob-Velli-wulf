package javawulf.model.powerUp;

import javawulf.model.Coordinate;

public class PowerUpDoublePoints extends PowerUpImpl {

    private final int DURATIONMILLI = 30_000;
    private final int POINTS = 100;
    private final String TYPE = "DoublePoints";
    
    public PowerUpDoublePoints(Coordinate position) {
        this.coordinates = position;
        this.type = TYPE;
        this.durationInMilli = DURATIONMILLI;
        this.pointsGiven = POINTS;
    }
}