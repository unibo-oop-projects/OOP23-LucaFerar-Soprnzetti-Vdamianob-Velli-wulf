package javawulf.model.powerUp;

import java.util.Optional;

import javawulf.model.player.Player;

public interface PowerUpHandler {

    void collectPowerUp(PowerUp powerUpPicked);

    void update(Player player);

    Optional<PowerUp> getPowerUpActive();
    
}
