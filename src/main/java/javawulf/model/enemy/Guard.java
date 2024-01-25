package javawulf.model.enemy;

import javawulf.model.BoundingBox;
import javawulf.model.PositionOnMap;
import javawulf.model.player.Player;
import javawulf.model.player.Sword.SwordType;

public class Guard extends EnemyImpl {

    private final SwordType killValue;
    private boolean isAlive;
    private boolean isStunned;
    private long stunTime;

    public Guard(BoundingBox collision, PositionOnMap position, Integer speed, BoundingBox hitBox, Integer points,
            SwordType killValue) {
        super(collision, position, speed, hitBox, points);
        this.killValue = killValue;
        this.isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isKillable(Player p) {
        return this.killValue.equals(p.getSwordType());
    }

    public void checkRoom() {
        // TODO implement here
    }

    public void stun(Integer time) {
        this.isStunned = true;
        this.stunTime = System.currentTimeMillis() + time * 1000;
    }

    @Override
    public void move() {
        if (this.isStunned) {
            if (System.currentTimeMillis() >= this.stunTime) {
                this.isStunned = false;
            }
        } else {
            throw new UnsupportedOperationException("Unimplemented method 'move'");
        }
    }

    @Override
    public void takeHit(Player p) {
        if (this.isKillable(p)) {
            this.setAlive(false);
        } else {
            this.stun(4);
        }
    }
}
