package javawulf.model.enemy;

import javawulf.model.Entity;

import javawulf.model.Coordinate;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;
import javawulf.model.player.Sword;

public abstract class EnemyImpl extends Entity implements Enemy {

    private int points;

    public EnemyImpl(Coordinate position, Integer speed, int points) {
        super(position, CollisionType.ENEMY, speed);
        this.points = points;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public abstract void move(Player p);

    public boolean inflictDamage(Player p) {
       return p.isHit(this.getBounds());
    }

    public abstract void takeHit(Player p);

    protected boolean isHit(Sword s) {
        return this.getBounds().isCollidingWith(s.getBounds().getCollisionArea())
                && s.getBounds().getCollisionType() == CollisionType.SWORD;
    }

}
