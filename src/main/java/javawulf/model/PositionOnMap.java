package javawulf.model;

import javafx.util.Pair;

public interface PositionOnMap {
    
    public Pair<Integer,Integer> getPosition();

    public void setPosition(Pair<Integer,Integer> p);

    public Integer getX();

    public Integer getY();
}
