package javawulf.model;

public interface Entity extends GameElement {

    public static final int DEFAULT_SPEED=1;
    public static final int DOUBLE_SPEED=2;

    public void setSpeed(Integer speed);

    public Direction getDirection();
    
}
