package javawulf.model.powerUp;

import javawulf.model.Coordinate;

public class PowerUpSpeed extends PowerUpImpl{

    private final static int DURATION = 30;
    private final static int POINTS = 50;
    private final static String TYPE = "Speed";

    public PowerUpSpeed(Coordinate position) {
        super(position, POINTS, TYPE, DURATION);
    }

}
