package javawulf.model;

public interface GameElement {
    
    public BoundingBox getBounds();

    public Coordinate getPosition();

    public void setBounds(BoundingBox b);

    public void setPosition(Coordinate p);
}
