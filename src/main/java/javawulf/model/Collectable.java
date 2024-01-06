package javawulf.model;

public abstract class Collectable extends GameObject {

    private PositionOnMap position;
    private Integer points;

    public Collectable(PositionOnMap position, Integer points) {
        this.position = position;
        this.points = points;
    }

    public Integer getPoints() {
        return this.points;
    }

    public PositionOnMap getPosition() {
        return this.position;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setPosition(PositionOnMap position) {
        this.position = position;
    }

    public abstract void collect();

    public abstract void applyEffect();

}