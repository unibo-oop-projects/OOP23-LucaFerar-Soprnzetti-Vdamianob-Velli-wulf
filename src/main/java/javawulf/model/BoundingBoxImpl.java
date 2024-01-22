package javawulf.model;

import java.awt.Rectangle;
import java.util.Optional;

public class BoundingBoxImpl implements BoundingBox {

    private Optional<Rectangle> area;
    private CollisionType type;

    public BoundingBoxImpl(int x, int y, int width, int height, CollisionType type){
        this.area = Optional.of(new Rectangle(x, y, width, height));
        this.type = type;
    };

    @Override
    public boolean isCollidingWith(Rectangle box) {
        return this.area.orElse(new Rectangle(0,0,0,0)).intersects(box);
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
