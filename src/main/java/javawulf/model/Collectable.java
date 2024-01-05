package javawulf.model;

import javafx.util.Pair;

public abstract class Collectable {

    private Pair<Integer, Integer> position;
    private Integer points;

    public Collectable(Pair<Integer, Integer> position, Integer points) {
        this.position = position;
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public abstract void collect();
    public abstract void applyEffect();

}