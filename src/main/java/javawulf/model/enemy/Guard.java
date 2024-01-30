package javawulf.model.enemy;

import javawulf.model.Coordinate;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.player.Player;

public class Guard extends EnemyImpl {

    private final static int KILLVALUE = 2;
    private final static int POINTS = 500;

    private boolean isAlive;
    private boolean isStunned;
    private long stunTime;

    public Guard(Coordinate position) {
        super(position);
        this.isAlive = true;
        this.isStunned = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isStunned() {
        return isStunned;
    }

    public boolean isKillable(Player p) {
        return p.getSword().getSwordStrength() == KILLVALUE;
    }

    public boolean checkRoom(Player p) {
        // TODO implement here Return true if player is in the same room as the guard, probably should be private
        return false;
    }

    public void stun(Integer time) {
        this.isStunned = true;
        this.stunTime = System.currentTimeMillis() + time * 1000;
    }

    @Override
    public void move(Player p, Map m) {
        if (this.isStunned || !this.checkRoom(p)) {
            if (System.currentTimeMillis() >= this.stunTime) {
                this.isStunned = false;
            }
        } else {

            int diffX = p.getPosition().getX() - this.getPosition().getX();
            int diffY = p.getPosition().getY() - this.getPosition().getY();

            if (!this.isCollidingWithWall(m)) {
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    this.getPosition().setPosition(
                            this.getPosition().getX() + (int) Math.signum(diffX) * this.getSpeed() * MOVEMENT_DELTA,
                            this.getPosition().getY());
                } else {
                    this.getPosition().setPosition(this.getPosition().getX(),
                            this.getPosition().getY() + (int) Math.signum(diffY) * this.getSpeed() * MOVEMENT_DELTA);
                }

                this.getBounds().setCollisionArea(this.getPosition().getX(), this.getPosition().getY(), OBJECT_SIZE,
                        OBJECT_SIZE);
            }
        }
    }

    @Override
    public void takeHit(Player p) {
        if (super.isHit(p.getSword().getBounds())) {
            if (this.isKillable(p)) {
                this.isAlive = false;
                p.getScore().addPoints(POINTS);
                this.getBounds().changeCollisionType(CollisionType.INACTIVE);
            } else {
                this.stun(5);
            }
        }
    }
}
