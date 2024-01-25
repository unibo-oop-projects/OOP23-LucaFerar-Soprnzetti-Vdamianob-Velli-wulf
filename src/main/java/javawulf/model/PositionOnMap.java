package javawulf.model;

import javafx.util.Pair;

public interface PositionOnMap {
    
    public Pair<Integer,Integer> getPosition();

    public void setPosition(int x, int y);

    public Integer getX();

    public Integer getY();
}
