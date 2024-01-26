package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;

public abstract class GameObject implements GameElement{

    public static final int OBJECT_SIZE = 24;
    private BoundingBox collision;
    private Coordinate position;

    public GameObject(Coordinate position, BoundingBox collision){
        this.position = position;
        this.collision = collision;
    }

    public GameObject(Coordinate position, CollisionType type){
        this(position, new BoundingBoxImpl(position.getX(), position.getY(), OBJECT_SIZE, OBJECT_SIZE, type));
    }

    public GameObject(BoundingBox collision, Coordinate position) {
        this.collision = collision;
        this.position = position;
    }

    public BoundingBox getBounds() {
        return this.collision;
    }

    public Coordinate getPosition() {
        return this.position;
    }

    public void setBounds(BoundingBox b) {
        this.collision = b;
    }

    public void setPosition(Coordinate p) {
        this.position = p;
    }

}
