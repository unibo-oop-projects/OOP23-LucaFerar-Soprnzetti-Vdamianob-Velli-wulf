package javawulf.model;

/**
 * Entity defines a GameElement with the ability to move.
 */
public interface Entity extends GameElement {

    int DEFAULT_SPEED = 1;
    int DOUBLE_SPEED = 2;

    void setSpeed(int speed);

    Direction getDirection();

    /**
     * Checks if the Entity is getting hit by another. If it is the case, then the
     * Entity will be subject to damage
     * 
     * @param box BoundingBox that must be checked
     * @return true if the Entity got hit, otherwise false
     */
    boolean isHit(BoundingBox box);

    /**
     * Reduces the stun duration of the Entity by one unit.
     */
    void reduceStun();

    /**
     * @param stun The value stun must be set to
     */
    void setStun(int stun);

}
