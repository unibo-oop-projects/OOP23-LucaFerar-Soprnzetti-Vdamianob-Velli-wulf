package javawulf.model;

import java.awt.Rectangle;
import java.util.Optional;

public final class BoundingBoxImpl implements BoundingBox {

    private Optional<Rectangle> area;
    private CollisionType type;

    public BoundingBoxImpl(final int x, final int y, final int width, final int height, final CollisionType type) {
        this.area = Optional.ofNullable(new Rectangle(x - width / 2, y - height / 2, width, height));
        this.type = type;
    }

    @Override
    public boolean isCollidingWith(final Rectangle box) {
        return this.area.orElse(new Rectangle()).intersects(box);
    }

    @Override
    public Rectangle getCollisionArea() {
        return this.area.orElse(new Rectangle());
    }

    @Override
    public void setCollisionArea(final int x, final int y, final int width, final int height) {
        this.area = Optional.ofNullable(new Rectangle(x - width / 2, y - height / 2, width, height));
    }

    @Override
    public CollisionType getCollisionType() {
        return this.type;
    }

    @Override
    public void changeCollisionType(final CollisionType type) {
        this.type = type;
    }

}
