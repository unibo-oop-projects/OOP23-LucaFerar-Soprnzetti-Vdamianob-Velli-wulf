package javawulf.model;

public interface GameElement {
    
    public BoundingBox getBounds();

    public PositionOnMap getPosition();

    public void setBounds(BoundingBox b);

    public void setPosition(PositionOnMap p);
}
