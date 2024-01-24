package javawulf.model.powerUp;

import javawulf.model.Player;

public interface PowerUpHandler {

    void collectPowerUp(PowerUp powerUpPicked);

    void update(Player player);
}
