package javawulf.model.powerUp;

import javawulf.model.player.Player;

public class PowerUpHandlerImpl implements PowerUpHandler{

    private PowerUp powerUpActive;

    @Override
    public void collectPowerUp(PowerUp powerUpPicked) {
        this.powerUpActive = powerUpPicked;
    }

    @Override
    public void update(Player player) {
        player.getScore().addPoints(powerUpActive.getPoints());
        while (powerUpActive.stillActive()) {
            powerUpActive.applyEffect(player);
        }
    }
    
}
