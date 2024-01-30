package javawulf.model.powerUp;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;
import javawulf.model.player.Score;

public class PowerUpDoublePoints extends PowerUpImpl {

    private final static int DURATIONMILLI = 30_000;
    private final static int POINTS = 100;
    private final static String TYPE = "DoublePoints";
    
    public PowerUpDoublePoints(Coordinate position) {
        super(position, POINTS, TYPE, DURATIONMILLI);
    }

    @Override
    public void applyEffect(Player p) {
        if(this.stillActive()) {
            p.getScore().setMultiplier(Score.Multiplier.DOUBLE);
        }
        else {
            p.getScore().setMultiplier(Score.Multiplier.DEFAULT);
        }
    }
}
