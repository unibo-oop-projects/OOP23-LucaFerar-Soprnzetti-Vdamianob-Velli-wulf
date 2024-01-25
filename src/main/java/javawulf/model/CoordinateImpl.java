package javawulf.model;

import java.awt.Point;

public class CoordinateImpl implements Coordinate {
    
    private Point position;

    public CoordinateImpl(int x, int y){
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    public Integer getX() {
        return (int) this.position.getX();
    }

    public Integer getY() {
        return (int) this.position.getY();
    }
}
