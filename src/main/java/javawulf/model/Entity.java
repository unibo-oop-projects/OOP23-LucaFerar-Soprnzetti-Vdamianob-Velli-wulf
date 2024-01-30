package javawulf.model;

public interface Entity extends GameElement {

    public static final int DEFAULT_SPEED=1;
    public static final int DOUBLE_SPEED=2;

    public void setSpeed(int speed);

    public Direction getDirection();

    /**
     * Check if the entity is getting hit by another. If it is the case, then the
     * entity will be subject to damage
     * 
     * @param box BoundingBox that must be checked
     */
    public boolean isHit(BoundingBox box);

    public void reduceStun();

    public void setStun(int stun);
    
}
