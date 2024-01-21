package javawulf.model;

import javafx.util.Pair;

public class PositionOnMapImpl implements PositionOnMap {
    
    private Pair<Integer, Integer> position;

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(Integer x, Integer y) {
        this.position = new Pair<Integer, Integer>(x, y);
    }

    public Integer getX() {
        return this.position.getKey();
    }

    public Integer getY() {
        return this.position.getValue();
    }
}