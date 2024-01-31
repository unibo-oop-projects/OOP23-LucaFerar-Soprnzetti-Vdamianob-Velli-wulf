package javawulf.model.powerUp;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;
import javawulf.model.player.Score;

public class PowerUpDoublePoints extends PowerUpImpl {

    private final static int DURATION = 30;
    private final static int POINTS = 100;
    private final static String TYPE = "DoublePoints";
    
    public PowerUpDoublePoints(Coordinate position) {
        super(position, POINTS, TYPE, DURATION);
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
