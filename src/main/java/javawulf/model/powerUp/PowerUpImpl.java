package javawulf.model.powerUp;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public abstract class PowerUpImpl extends AbstractCollectable implements PowerUp {

    protected int duration;
    protected String type;

    public PowerUpImpl(final Coordinate position, final Integer points, final String type, final int duration) {
        super(position, points);
        this.type = type;
        this.duration = duration;
    }
    
    public int getDuration() {
        return this.duration;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public boolean stillActive() {
        return duration > 0;
    }
    
    @Override
    public void updateDuration() {
        if (duration > 0) {
            duration--;
        }
    }

    @Override
    public String toString() {
        return "PowerUp: [duration=" + this.getDuration() + ", type=" + this.getType() + ", pointsGiven=" + this.getPoints() + "]";
    }

    @Override
    public void applyEffect(final Player p) {
        p.getPowerUpHandler().collectPowerUp(this, p);
    }

    @Override
    public void finishEffect(Player p) {
        this.duration = 0;
        this.resetEffect(p);
    }

    public abstract void resetEffect(Player p);

}
