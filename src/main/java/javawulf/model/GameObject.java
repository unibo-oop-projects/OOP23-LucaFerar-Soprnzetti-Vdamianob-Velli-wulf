package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;

public abstract class GameObject implements GameElement{

    private BoundingBox collision;
    private PositionOnMap position;

    public GameObject(PositionOnMap position, BoundingBox collision){
        this.position = position;
        this.collision = collision;
    }

    public GameObject(PositionOnMap position, CollisionType type){
        //the size of the element should be factor, likely through a parameter of the constructor
        //or as a private static final variable (as it could be the standard one),
        //otherwise it would be difficult to calculate the area surronding the position of the element
        //which in this case would be the standard one (player/enemy/normal sword/collectables)
        //which is a square basically
        this(position, new BoundingBoxImpl(0, 0, 0, 0, type));
    }

    public BoundingBox getBounds(){
        return this.collision;
    }

    public PositionOnMap getPosition(){
        return this.position;
    }

    public void setBounds(BoundingBox b){
        this.collision = b;
    }

    public void setPosition(PositionOnMap p){
        this.position = p;
    }
    
}
