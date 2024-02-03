package javawulf.model.powerUp;

import javawulf.model.Entity;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class PowerUpSpeed extends PowerUpImpl {

    public PowerUpSpeed(final Coordinate position, final int duration, final int points, final String type) {
        super(position, points, type, duration);
    }

    @Override
    public void applyEffect(Player p) {
        if(this.stillActive()) {
            p.setSpeed(Entity.DOUBLE_SPEED);
        } else {
            p.setSpeed(Entity.DEFAULT_SPEED);
        }
    }

}
