package javawulf.model.powerUp;

import java.util.Optional;

import javawulf.model.player.Player;

public class PowerUpHandlerImpl implements PowerUpHandler{

    private Optional<PowerUp> powerUpActive;

    public PowerUpHandlerImpl() {
        this.powerUpActive = Optional.empty();
    }

    @Override
    public void collectPowerUp(final PowerUp powerUpPicked) {
        this.powerUpActive = Optional.of(powerUpPicked);
    }

    @Override
    public void update(Player player) {
        if (powerUpActive.isPresent()) {
            powerUpActive.get().updateDuration();
            this.checkPowerUpActive();
        }
        powerUpActive.get().applyEffect(player);
        changePlayerColor(player);
    }

    @Override
    public Optional<PowerUp> getPowerUpActive() {
        return powerUpActive;
    }

    private void checkPowerUpActive() {
        if (!powerUpActive.get().stillActive()) {
            powerUpActive = Optional.empty();
        }
    }

    private void changePlayerColor(Player player) {
        if (this.getPowerUpActive().isPresent()) {
            if (powerUpActive.get().getType() == "Attack") {
                player.setColor(Player.PlayerColor.STRENGTH);
            }
            if (powerUpActive.get().getType() == "DoublePoints") {
                player.setColor(Player.PlayerColor.DOUBLE_POINTS);
            }
            if (powerUpActive.get().getType() == "Invincibility") {
                player.setColor(Player.PlayerColor.INVULNERABILITY);
            }
            if (powerUpActive.get().getType() == "Speed") {
                player.setColor(Player.PlayerColor.SPEED);
            }
        } else {
            player.setColor(Player.PlayerColor.NONE);
        }
    }
    
}
