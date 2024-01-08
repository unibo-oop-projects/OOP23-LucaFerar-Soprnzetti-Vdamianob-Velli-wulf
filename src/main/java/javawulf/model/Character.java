package javawulf.model;

public abstract class Character extends GameObject {
    private Integer speed;
    private BoundingBox hitBox;

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

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public void setHitBox(BoundingBox hitBox) {
        this.hitBox = hitBox;
    }

}
