package javawulf.model;

import javafx.util.Pair;

public class PositionOnMapImpl implements PositionOnMap {
    
    private Pair<Integer, Integer> position;

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public Integer getX() {
        return this.position.getKey();
    }

    public Integer getY() {
        return this.position.getValue();
    }
}
