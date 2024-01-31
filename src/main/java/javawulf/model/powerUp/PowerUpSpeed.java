package javawulf.model.powerUp;

import javawulf.model.Entity;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class PowerUpSpeed extends PowerUpImpl{

    private final static int DURATION = 30;
    private final static int POINTS = 50;
    private final static String TYPE = "Speed";

    public PowerUpSpeed(Coordinate position) {
        super(position, POINTS, TYPE, DURATION);
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
