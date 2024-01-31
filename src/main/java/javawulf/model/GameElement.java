package javawulf.model;

public interface GameElement {

    BoundingBox getBounds();

    Coordinate getPosition();

    void setBounds(BoundingBox b);

    void setPosition(Coordinate p);
}
