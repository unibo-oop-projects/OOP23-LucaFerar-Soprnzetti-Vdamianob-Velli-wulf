package javawulf.model;

import javawulf.model.PlayerHealth.ShieldStatus;

public class Shield extends Collectable implements Item {

    private int duration;

    public Shield(PositionOnMap position, Integer points, Integer duration) {
        super(position, points);
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
    }

    public void consume() {
        this.duration--;
    }

    @Override
    public void applyEffect(Player p) {
        p.getPlayerHealth().setShieldStatus(ShieldStatus.FULL);
    }

}
