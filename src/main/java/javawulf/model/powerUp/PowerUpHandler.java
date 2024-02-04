package javawulf.model.powerUp;

import java.util.Optional;

import javawulf.model.player.Player;

public interface PowerUpHandler {

    void collectPowerUp(final PowerUp powerUpPicked, Player player);

    void update(Player player);

    Optional<PowerUp> getPowerUpActive();
    
}
