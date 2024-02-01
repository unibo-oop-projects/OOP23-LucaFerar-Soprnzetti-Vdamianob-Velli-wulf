package javawulf.model.powerUp;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;
import javawulf.model.player.Score;

public class PowerUpDoublePoints extends PowerUpImpl {
    
    public PowerUpDoublePoints(Coordinate position, int duration, int points, String type) {
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
