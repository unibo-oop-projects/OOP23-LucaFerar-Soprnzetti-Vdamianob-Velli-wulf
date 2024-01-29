package javawulf.model.enemy;

import javawulf.model.AbstractEntity;

import javawulf.model.Coordinate;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;

public abstract class EnemyImpl extends AbstractEntity implements Enemy {

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

    public abstract void takeHit(Player p);

}
