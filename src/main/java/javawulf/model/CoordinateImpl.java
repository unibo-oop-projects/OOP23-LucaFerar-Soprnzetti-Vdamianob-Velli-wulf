package javawulf.model;

import java.awt.Point;

public final class CoordinateImpl implements Coordinate {

    private Point position;

    public CoordinateImpl(final int x, final int y) {
        this.position = new Point(x, y);
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(final int x, final int y) {
        this.position = new Point(x, y);
    }

    @Override
    public Integer getX() {
        return (int) this.position.getX();
    }

    @Override
    public Integer getY() {
        return (int) this.position.getY();
    }
}
