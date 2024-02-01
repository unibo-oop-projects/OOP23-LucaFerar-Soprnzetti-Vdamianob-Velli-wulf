package javawulf.model.powerUp;

import javawulf.model.Coordinate;

public interface PowerUpFactory {

    public PowerUpAttack createPowerUpAttack(Coordinate coordinates);

    public PowerUpDoublePoints createPowerUpDoublePoints(Coordinate coordinates);

    public PowerUpInvincibility createPowerUpInvincibility(Coordinate coordinates);

    public PowerUpSpeed createPowerUpSpeed(Coordinate coordinates);
    
}
