package javawulf.model.enemy;

import javawulf.model.AbstractEntity;
import javawulf.model.BoundingBox;
import javawulf.model.Coordinate;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.player.Player;

public abstract class EnemyImpl extends AbstractEntity implements Enemy {

    public EnemyImpl(Coordinate position) {
        super(position, CollisionType.ENEMY, DEFAULT_SPEED);
    }

    public abstract void move(Player p, Map m);

    public abstract void takeHit(Player p);

    @Override
    protected boolean control(BoundingBox box) {
        return box.getCollisionType().equals(CollisionType.SWORD) 
            && this.getBounds().getCollisionType().equals(CollisionType.ENEMY);
    }

}
