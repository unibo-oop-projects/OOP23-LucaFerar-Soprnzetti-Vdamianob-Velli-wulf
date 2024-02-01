package javawulf.model.powerUp;

import javawulf.model.Coordinate;

public class PowerUpFactoryImpl implements PowerUpFactory {

    private final static int DURATION_ATTACK = 20;
    private final static int POINTS_ATTACK = 50;
    private final static String TYPE_ATTACK = "Attack";

    private final static int DURATION_DOUBLEPOINTS = 30;
    private final static int POINTS_DOUBLEPOINTS = 100;
    private final static String TYPE_DOUBLEPOINTS = "DoublePoints";

    private final static int DURATION_INVINCIBILITY = 10;
    private final static int POINTS_INVINCIBILITY = 100;
    private final static String TYPE_INVINCIBILITY = "Invincibility";   

    private final static int DURATION_SPEED = 30;
    private final static int POINTS_SPEED = 50;
    private final static String TYPE_SPEED = "Speed";

    @Override
    public PowerUpAttack createPowerUpAttack(Coordinate coordinates) {
        return new PowerUpAttack(coordinates, DURATION_ATTACK, POINTS_ATTACK, TYPE_ATTACK);
    }

    @Override
    public PowerUpDoublePoints createPowerUpDoublePoints(Coordinate coordinates) {
        return new PowerUpDoublePoints(coordinates, DURATION_DOUBLEPOINTS, POINTS_DOUBLEPOINTS, TYPE_DOUBLEPOINTS);
    }

    @Override
    public PowerUpInvincibility createPowerUpInvincibility(Coordinate coordinates) {
        return new PowerUpInvincibility(coordinates, DURATION_INVINCIBILITY, POINTS_INVINCIBILITY, TYPE_INVINCIBILITY);
    }

    @Override
    public PowerUpSpeed createPowerUpSpeed(Coordinate coordinates) {
        return new PowerUpSpeed(coordinates, DURATION_SPEED, POINTS_SPEED, TYPE_SPEED);
    }

}
