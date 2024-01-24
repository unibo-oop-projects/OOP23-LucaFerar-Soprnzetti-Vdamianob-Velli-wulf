package javawulf.model;

import javafx.util.Pair;

public interface PositionOnMap {
    
    public Pair<Integer,Integer> getPosition();

    public void setPosition(Integer x, Integer y);

    public Integer getX();

    public Integer getY();
}
