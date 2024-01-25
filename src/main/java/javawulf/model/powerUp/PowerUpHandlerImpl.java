package javawulf.model.powerUp;

import javawulf.model.player.Player;

public class PowerUpHandlerImpl implements PowerUpHandler{

    private PowerUp powerUpActive;

    @Override
    public void collectPowerUp(PowerUp powerUpPicked) {
        this.powerUpActive = powerUpPicked;
        powerUpPicked.activateEffect();
    }

    @Override
    public void update(Player player) {
        while (powerUpActive.stillActive()) {
            //here i need to change player stats
        }
    }
    
}
