package javawulf.model;

import java.awt.Point;

public interface Coordinate {
    
    public Point getPosition();

    public void setPosition(int x, int y);

    public Integer getX();

    public Integer getY();
}
