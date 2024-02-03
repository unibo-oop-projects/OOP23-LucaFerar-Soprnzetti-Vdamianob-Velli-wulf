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
        if(this.stillActive()) {
            p.getScore().setMultiplier(Score.Multiplier.DOUBLE);
        } else {
            p.getScore().setMultiplier(Score.Multiplier.DEFAULT);
        }
    }
    
}
