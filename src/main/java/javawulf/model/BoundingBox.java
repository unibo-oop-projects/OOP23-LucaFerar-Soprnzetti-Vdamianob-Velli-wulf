package javawulf.model;

import java.awt.Rectangle;

public interface BoundingBox {

    enum CollisionType {
        PLAYER,
        ENEMY,
        COLLECTABLE,
        SWORD,
        STUNNED, //could get renamed in the future
        INACTIVE; //could work also for defeated characters
    }

    boolean isCollidingWith(Rectangle box);

    Rectangle getCollisionArea();

    void setCollisionArea(int x, int y, int width, int height);

    CollisionType getCollisionType();

    void changeCollisionType(CollisionType type);
}
