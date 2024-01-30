package javawulf.model.powerUp;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;

public abstract class PowerUpImpl extends AbstractCollectable implements PowerUp {

    protected int duration;
    protected String type;
    protected long activationTimeInMilli;

    public PowerUpImpl(Coordinate position, Integer points, String type, int duration) {
        super(position, points);
        this.type = type;
        this.duration = duration;
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
        return "PowerUp: [duration=" + duration + ", type=" + type + ", pointsGiven=" + this.getPoints() + "]";
    }
    
}
