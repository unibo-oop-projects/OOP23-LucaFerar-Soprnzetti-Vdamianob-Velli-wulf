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
    
    public void activateEffect() {
        activationTimeInMilli = System.currentTimeMillis();
    }

    @Override
    public void applyEffect(Player player) {
        activateEffect();
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
