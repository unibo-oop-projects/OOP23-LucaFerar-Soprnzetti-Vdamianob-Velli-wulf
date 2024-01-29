package javawulf.model;

import java.awt.Rectangle;

public interface BoundingBox {

    public enum CollisionType{
        PLAYER,
        ENEMY,
        COLLECTABLE,
        SWORD,
        STUNNED, //could get renamed in the future
        INACTIVE; //could work also for defeated characters
    }

    public boolean isCollidingWith(Rectangle box);

    public Rectangle getCollisionArea();

    public void setCollisionArea(int x, int y, int width, int height);

    public CollisionType getCollisionType();

    public void changeCollisionType(CollisionType type);
}
