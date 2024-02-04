package javawulf.model.powerUp;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;
import javawulf.model.player.Score;

public class PowerUpDoublePoints extends PowerUpImpl {
    
    public PowerUpDoublePoints(final Coordinate position, final int duration, final int points, final String type) {
        super(position, points, type, duration);
    }

    @Override
    public void applyEffect(Player p) {
        super.applyEffect(p);
        if(this.stillActive()) {
            p.getScore().setMultiplier(Score.Multiplier.DOUBLE);
        } else {
            resetEffect(p);
        }
    }

    @Override
    public void resetEffect(Player p) {
        p.getScore().setMultiplier(Score.Multiplier.DEFAULT);
    }
    
}
