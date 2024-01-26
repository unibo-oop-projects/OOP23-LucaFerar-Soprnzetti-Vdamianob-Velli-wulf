package javawulf.model.powerUp;

import javawulf.model.Coordinate;

public class PowerUpSpeed extends PowerUpImpl{

    private final int DURATIONMILLI = 30_000;
    private final int POINTS = 50;
    private final String TYPE = "Speed";

    public PowerUpSpeed(Coordinate position, Integer points, String type, int durationInMilli) {
        super(position, points, type, durationInMilli);
    }
}
