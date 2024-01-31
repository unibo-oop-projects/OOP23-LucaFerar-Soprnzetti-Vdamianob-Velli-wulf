package javawulf.model;

import java.awt.Point;

public interface Coordinate {

    Point getPosition();

    void setPosition(int x, int y);

    Integer getX();

    Integer getY();
}
