package javawulf.model;

public abstract class Entity extends GameObject {
    private Integer speed;
    private BoundingBox hitBox;

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
