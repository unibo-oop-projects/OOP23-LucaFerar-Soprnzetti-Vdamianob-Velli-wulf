package javawulf.model.powerUp;

import javawulf.model.Coordinate;

public class PowerUpDoublePoints extends PowerUpImpl {

    private final static int DURATIONMILLI = 30_000;
    private final static int POINTS = 100;
    private final static String TYPE = "DoublePoints";
    
    public PowerUpDoublePoints(Coordinate position) {
        super(position, POINTS, TYPE, DURATIONMILLI);
    }
}
