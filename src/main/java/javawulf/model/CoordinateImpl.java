package javawulf.model;

import java.awt.Point;

/**
 * Implementation of Coordinate.
 */
public final class CoordinateImpl implements Coordinate {

    private Point position;

    /**
     * Creates a new coordinate.
     * 
     * @param x The position on the X-axis
     * @param y the position on the Y-axis
     */
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
