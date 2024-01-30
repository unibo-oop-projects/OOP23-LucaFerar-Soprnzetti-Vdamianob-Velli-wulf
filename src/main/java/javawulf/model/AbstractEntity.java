package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.map.TileType;

/**
 * Abstract implementation of the Entity interface.
 */
public abstract class AbstractEntity extends GameObject implements Entity {

    public static final int MOVEMENT_DELTA = OBJECT_SIZE / 8;
    private int speed;
    private Direction direction;
    private int stun;

    /**
     * Creates an Entity.
     * 
     * @param position the position of the entity
     * @param type     the collision type of the entity
     * @param speed    the speed of the entity
     */
    public AbstractEntity(final Coordinate position, final CollisionType type, final int speed) {
        super(position, type);
        this.speed = speed;
        this.direction = Direction.DOWN;
    }

    /**
     * @return the speed of the entity.
     */
    public final int getSpeed() {
        return this.speed;
    }

    /**
     * @return the direction of the entity.
     */
    public final Direction getDirection() {
        return this.direction;
    }

    /**
     * Sets the speed of the entity.
     * 
     * @param speed the new speed the entity will have
     */
    public final void setSpeed(final int speed) {
        this.speed = speed;
    }

    /**
     * Changes the direction of the entity.
     * 
     * @param direction the new direction the entity will have
     */
    public final void setDirection(final Direction direction) {
        this.direction = direction;
    }

    /**
     * Checks if the entity is colliding with a wall.
     * 
     * @param m the map which the entity is in
     * @return true if the entity is colliding with a wall, false otherwise
     */
    protected final boolean isCollidingWithWall(final Map m) {
        var tile = m.getTileTypes(this.getBounds());
        return tile.contains(TileType.WALL);
    }

    public boolean isHit(final BoundingBox box) {
        return this.getBounds().isCollidingWith(box.getCollisionArea())
                && control(box);
    }

    protected abstract boolean control(BoundingBox box);

    public final void reduceStun() {
        if (this.getBounds().getCollisionType().equals(CollisionType.STUNNED)) {
            if (stun == 0) {
                this.getBounds().changeCollisionType(originalCollisonType());
            } else {
                this.stun--;
            }
        }
    }

    protected abstract CollisionType originalCollisonType();

    public final void setStun(final int stun) {
        this.stun = stun;
    }
}
