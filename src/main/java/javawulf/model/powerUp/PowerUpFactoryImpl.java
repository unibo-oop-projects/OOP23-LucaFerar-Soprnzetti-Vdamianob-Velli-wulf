package javawulf.model.powerUp;

import javawulf.model.Coordinate;

public class PowerUpFactoryImpl implements PowerUpFactory {

    public final static int DURATION_ATTACK = 20;
    public final static int POINTS_ATTACK = 50;
    public final static String TYPE_ATTACK = "Attack";

    public final static int DURATION_DOUBLEPOINTS = 30;
    public final static int POINTS_DOUBLEPOINTS = 100;
    public final static String TYPE_DOUBLEPOINTS = "DoublePoints";

    public final static int DURATION_INVINCIBILITY = 10;
    public final static int POINTS_INVINCIBILITY = 100;
    public final static String TYPE_INVINCIBILITY = "Invincibility";   

    public final static int DURATION_SPEED = 30;
    public final static int POINTS_SPEED = 50;
    public final static String TYPE_SPEED = "Speed";

    @Override
    public PowerUpAttack createPowerUpAttack(final Coordinate coordinates) {
        return new PowerUpAttack(coordinates, DURATION_ATTACK, POINTS_ATTACK, TYPE_ATTACK);
    }

    @Override
    public PowerUpDoublePoints createPowerUpDoublePoints(final Coordinate coordinates) {
        return new PowerUpDoublePoints(coordinates, DURATION_DOUBLEPOINTS, POINTS_DOUBLEPOINTS, TYPE_DOUBLEPOINTS);
    }

    @Override
    public PowerUpInvincibility createPowerUpInvincibility(final Coordinate coordinates) {
        return new PowerUpInvincibility(coordinates, DURATION_INVINCIBILITY, POINTS_INVINCIBILITY, TYPE_INVINCIBILITY);
    }

    @Override
    public PowerUpSpeed createPowerUpSpeed(final Coordinate coordinates) {
        return new PowerUpSpeed(coordinates, DURATION_SPEED, POINTS_SPEED, TYPE_SPEED);
    }

}
