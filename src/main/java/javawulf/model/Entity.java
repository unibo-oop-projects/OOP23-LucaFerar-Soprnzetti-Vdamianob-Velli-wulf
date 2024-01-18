package javawulf.model;

public abstract class Entity extends GameObject {
    private Integer speed;
    private BoundingBox hitBox;
    private Direction direction;

    public Character(BoundingBox collision, PositionOnMap position, Integer speed, BoundingBox hitBox) {
        super(collision, position);
        this.speed = speed;
        this.hitBox = hitBox;
    }

    public Integer getSpeed() {
        return this.speed;
    }

    public BoundingBox getHitBox() {
        return this.hitBox;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public void setHitBox(BoundingBox hitBox) {
        this.hitBox = hitBox;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
