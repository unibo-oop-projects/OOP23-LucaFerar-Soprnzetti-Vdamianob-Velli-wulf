package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;

public abstract class Entity extends GameObject {
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

}
