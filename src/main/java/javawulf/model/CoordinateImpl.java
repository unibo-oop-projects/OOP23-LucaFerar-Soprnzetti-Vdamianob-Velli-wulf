package javawulf.model;

import java.awt.Point;

public final class CoordinateImpl implements Coordinate {

    private Point position;

    public CoordinateImpl(final int x, final int y) {
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(final int x, final int y) {
        this.position = new Point(x, y);
    }

    public Integer getX() {
        return (int) this.position.getX();
    }

    public Integer getY() {
        return (int) this.position.getY();
    }
}
