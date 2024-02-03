package javawulf.model.powerUp;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;

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
    
}
