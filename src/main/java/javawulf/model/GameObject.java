package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;

/**
 * Implementation of the GameElement interface.
 */
public abstract class GameObject implements GameElement {

    public static final int OBJECT_SIZE = 24;
    private BoundingBox collision;
    private Coordinate position;

    /**
     * Creates a new GameObject with a custom BoundingBox.
     * 
     * @param position the position of the object
     * @param collision the collision type of the object
     */
    public GameObject(final Coordinate position, final BoundingBox collision) {
        this.position = position;
        this.collision = collision;
    }

    /**
     * Creates a new GameObject with a default BoundingBox.
     * 
     * @param position the position of the object
     * @param type the collision type of the object
     */
    public GameObject(final Coordinate position, final CollisionType type) {
        this(position, new BoundingBoxImpl(position.getX(), position.getY(), OBJECT_SIZE, OBJECT_SIZE, type));
    }

    @Override
    public final BoundingBox getBounds() {
        return this.collision;
    }

    @Override
    public final Coordinate getPosition() {
        return this.position;
    }

    @Override
    public final void setBounds(final BoundingBox b) {
        this.collision = b;
    }

    @Override
    public final void setPosition(final Coordinate p) {
        this.position = p;
    }

}
