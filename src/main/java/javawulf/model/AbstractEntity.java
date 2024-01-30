package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.map.TileType;

public abstract class AbstractEntity extends GameObject implements Entity {
    
    public static final int MOVEMENT_DELTA = OBJECT_SIZE/8;
    private int speed;
    private Direction direction;
    private int stun;

    public AbstractEntity(Coordinate position, CollisionType type, int speed) {
        super(position, type);
        this.speed = speed;
        this.direction = Direction.DOWN;
    }

    public int getSpeed() {
        return this.speed;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    protected boolean isCollidingWithWall(Map m){
        var tile = m.getTileTypes(this.getBounds());
        return tile.contains(TileType.WALL);
    }

    public boolean isHit(BoundingBox box){
        return this.getBounds().isCollidingWith(box.getCollisionArea())
            && control(box);
    }

    protected abstract boolean control(BoundingBox box);

    public void reduceStun(){
        if (this.getBounds().getCollisionType().equals(CollisionType.STUNNED)){
            if (stun==0){
                this.getBounds().changeCollisionType(originalCollisonType());
            } else {
                this.stun--;
            }
        }
    }

    protected abstract CollisionType originalCollisonType();

    public void setStun(int stun){
        this.stun = stun;
    }
}
