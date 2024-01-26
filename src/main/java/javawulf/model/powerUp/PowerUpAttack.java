package javawulf.model.powerUp;

import javawulf.model.Coordinate;

public class PowerUpAttack extends PowerUpImpl {
    
    private final static int DURATIONMILLI = 20_000;
    private final static int POINTS = 50;
    private final static String TYPE = "Attack";
    
    public PowerUpAttack(Coordinate position) {
        super(position, POINTS, TYPE, DURATIONMILLI);
    }
}
