package javawulf.model.powerUp;

import javawulf.model.Collectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public abstract class PowerUpImpl extends Collectable implements PowerUp {

    protected int durationInMilli;
    protected String type;
    protected long activationTimeInMilli;

    public PowerUpImpl(Coordinate position, Integer points, String type, int durationInMilli) {
        super(position, points);
        this.type = type;
        this.durationInMilli = durationInMilli;
    }

    @Override
    public void applyEffect(Player player) {
        activationTimeInMilli = System.currentTimeMillis();
    }
    
    @Override
    public boolean stillActive() {
        return activationTimeInMilli + durationInMilli < System.currentTimeMillis();
    }
    
    @Override
    public String toString() {
        return "PowerUp: [durationInMilli=" + durationInMilli + ", type=" + type + ", pointsGiven=" + this.getPoints() + "]";
    }
    
}
