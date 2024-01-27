package javawulf.model;

import java.util.Optional;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.map.TileType;

public abstract class Entity extends GameObject {
    
    public static final int MOVEMENT_DELTA = OBJECT_SIZE/8;
    private Integer speed;
    private Direction direction;

    public Entity(Coordinate position, CollisionType type, Integer speed) {
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
        Optional<TileType> tile = m.getTileType(this.getPosition());
        if (tile.isPresent()){
            return !tile.get().isCrossable();
        } else {
            return true;
        }
    }
}
