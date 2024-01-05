package javawulf.model;

import javafx.util.Pair;

public abstract class Collectable {

    private Pair<Integer, Integer> position;
    private Integer points;

    public Integer getPoints() {
        return this.points;
    }

    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public abstract void collect();

    public abstract void applyEffect();

}