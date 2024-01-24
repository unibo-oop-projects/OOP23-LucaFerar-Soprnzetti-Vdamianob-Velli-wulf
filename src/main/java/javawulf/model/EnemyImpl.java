package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;

public abstract class EnemyImpl extends Entity implements Enemy {

    private int points;

    public EnemyImpl(PositionOnMap position, Integer speed, int points) {
        super(position, CollisionType.ENEMY, speed);
        this.points = points;
    }

    public Integer getPoints() {
        return this.points;
    } 

    public void setPoints(int points) {
        this.points = points;
    }

    public abstract void move();

    public void inflictDamage(Player p) {
        if (this.isPlayerColliding(p)) {
            p.isHit(this.getBounds());
        }
    }

    private boolean isPlayerColliding(Player p) {
        return this.getBounds().isCollidingWith(p.getBounds().getCollisionArea()) && p.getBounds().getCollisionType() == CollisionType.PLAYER;
    }

    public abstract void takeHit(Player p);

    protected boolean isHit(Sword s) {
        return this.getBounds().isCollidingWith(s.getBounds().getCollisionArea()) && s.getBounds().getCollisionType() == CollisionType.SWORD;
    }
}
