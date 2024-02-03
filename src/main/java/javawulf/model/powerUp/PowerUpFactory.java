package javawulf.model.powerUp;

import javawulf.model.Coordinate;

public interface PowerUpFactory {

    public PowerUpAttack createPowerUpAttack(final Coordinate coordinates);

    public PowerUpDoublePoints createPowerUpDoublePoints(final Coordinate coordinates);

    public PowerUpInvincibility createPowerUpInvincibility(final Coordinate coordinates);

    public PowerUpSpeed createPowerUpSpeed(final Coordinate coordinates);
    
}
