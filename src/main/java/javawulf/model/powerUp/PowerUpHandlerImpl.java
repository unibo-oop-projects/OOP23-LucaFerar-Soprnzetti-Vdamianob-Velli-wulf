package javawulf.model.powerUp;

import java.util.Optional;

import javawulf.model.player.Player;

public class PowerUpHandlerImpl implements PowerUpHandler{

    private Optional<PowerUp> powerUpActive;

    public PowerUpHandlerImpl() {
        this.powerUpActive = Optional.empty();
    }

    @Override
    public void collectPowerUp(final PowerUp powerUpPicked, Player player) {
        if(powerUpActive.isPresent()){
            powerUpActive.get().finishEffect(player);
        }
        this.powerUpActive = Optional.of(powerUpPicked);
    }

    @Override
    public void update(Player player) {
        if (powerUpActive.isPresent()) {
            powerUpActive.get().updateDuration();
            if (checkUpFinished()) {
                powerUpActive.get().finishEffect(player);
                powerUpActive = Optional.empty();
            }
        }
        changePlayerColor(player);
    }

    @Override
    public Optional<PowerUp> getPowerUpActive() {
        return powerUpActive;
    }

    private boolean checkUpFinished() {
        return !powerUpActive.get().stillActive();
    }

    private void changePlayerColor(Player player) {
        if (this.getPowerUpActive().isPresent()) {
            if (PowerUpType.ATTACK.getType().equals(powerUpActive.get().getType())) {
                player.setColor(Player.PlayerColor.STRENGTH);
            }
            if (PowerUpType.DOUBLE_POINTS.getType().equals(powerUpActive.get().getType())) {
                player.setColor(Player.PlayerColor.DOUBLE_POINTS);
            }
            if (PowerUpType.INVINCIBILITY.getType().equals(powerUpActive.get().getType())) {
                player.setColor(Player.PlayerColor.INVULNERABILITY);
            }
            if (PowerUpType.SPEED.getType().equals(powerUpActive.get().getType())) {
                player.setColor(Player.PlayerColor.SPEED);
            }
        } else {
            player.setColor(Player.PlayerColor.NONE);
        }
    }
    
}
