package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.map.TileType;

public abstract class AbstractEntity extends GameObject implements Entity {
    
    public static final int MOVEMENT_DELTA = OBJECT_SIZE/8;
    private Integer speed;
    private Direction direction;

    public AbstractEntity(Coordinate position, CollisionType type, Integer speed) {
        super(position, type);
        this.speed = speed;
        this.direction = Direction.DOWN;
    }

    public Integer getSpeed() {
        return this.speed;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    protected boolean isCollidingWithWall(Map m){
        var tile = m.getTileTypes(this.getBounds());
        return tile.contains(TileType.WALL);
    }
}
