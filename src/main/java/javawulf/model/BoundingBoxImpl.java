package javawulf.model;

import java.awt.Rectangle;
import java.util.Optional;

public class BoundingBoxImpl implements BoundingBox {

    private Optional<Rectangle> area;
    private CollisionType type;

    public BoundingBoxImpl(int x, int y, int width, int height, CollisionType type){
        this.area = Optional.ofNullable(new Rectangle(x, y, width, height));
        this.type = type;
    };

    @Override
    public boolean isCollidingWith(Rectangle box) {
        return this.area.orElse(new Rectangle()).intersects(box);
    }

    @Override
    public Rectangle getCollisionArea() {
        return this.area.orElse(new Rectangle());
    }

    @Override
    public void setCollisionArea(int x, int y, int width, int height) {
        this.area = Optional.ofNullable(new Rectangle(x, y, width, height));
    }

    @Override
    public CollisionType getCollisionType() {
        return this.type;
    }

    @Override
    public void changeCollisionType(CollisionType type) {
        this.type = type;
    }
    
}
