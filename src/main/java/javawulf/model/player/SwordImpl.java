package javawulf.model.player;

import javawulf.model.BoundingBoxImpl;
import javawulf.model.Direction;
import javawulf.model.GameObject;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.BoundingBox.CollisionType;

public class SwordImpl extends GameObject implements Sword {

    private final static int NORMAL = 1;
    private final static int STRONG = 2;
    private final static int DURABILITY = 50;
    private int strength;
    private int durability;
    private SwordType swordType;
    private Direction swordDirection;

    /**
     * Creates a new sword by using the specified position
     * 
     * @param position The exact position the sword must be in when it is created
     * @param direction The direction the sword must face when it is created
     */
    public SwordImpl(Coordinate position, Direction direction) {
        super(new CoordinateImpl(position.getX()+(int)(direction.getX()*OBJECT_SIZE),
            position.getY()+(int)(direction.getY()*OBJECT_SIZE)),
            new BoundingBoxImpl(position.getX()+(int)(direction.getX()*OBJECT_SIZE),
            position.getY()+(int)(direction.getY()*OBJECT_SIZE), OBJECT_SIZE, OBJECT_SIZE,
            CollisionType.STUNNED));
        this.strength = NORMAL;
        this.swordType = SwordType.NORMAL;
        this.swordDirection = direction;
    }

    @Override
    public void move(Coordinate playerPosition, Direction playerDirection, int delta){
        updateDirection(playerDirection);
        this.getPosition().setPosition(playerPosition.getX() + (int)(this.swordDirection.getX()*delta),
            playerPosition.getY() + (int)(this.swordDirection.getY()*delta));
        updateCollisionArea();
    }

    private void updateDirection(Direction playerDirection){
        Direction movementDirection = playerDirection;
        if (checkIfDiagonal(playerDirection)){
            if(checkIfOpposite(playerDirection)){
                movementDirection = (int) playerDirection.getX() < 0 ? Direction.LEFT : Direction.RIGHT;
            } else {
                movementDirection = swordDirection;
            }
        }
        this.swordDirection = movementDirection;
    }

    private boolean checkIfDiagonal(Direction playerDirection){
        return playerDirection.equals(Direction.DOWN_LEFT) || playerDirection.equals(Direction.DOWN_RIGHT) ||
            playerDirection.equals(Direction.UP_LEFT) || playerDirection.equals(Direction.UP_RIGHT);
    }

    private boolean checkIfOpposite(Direction playerDirection){
        return Math.signum(playerDirection.getX()) != Math.signum(this.swordDirection.getX()) &&
            Math.signum(playerDirection.getY()) != Math.signum(this.swordDirection.getY());
    }

    private void updateCollisionArea(){
        int constantWidth=1;
        int constantHeight=1;
        if(this.getSwordType().equals(SwordType.GREATSWORD)){
            if(Math.abs((int)this.swordDirection.getX()) > 0){
                constantHeight = 3;
            } else {
                constantWidth = 3;
            }
        }
        this.getBounds().setCollisionArea(this.getPosition().getX(), this.getPosition().getY(),
            constantWidth*OBJECT_SIZE, constantHeight*OBJECT_SIZE);
    }

    @Override
    public void activate(){
        this.getBounds().changeCollisionType(CollisionType.SWORD);
        if (this.swordType.equals(SwordType.GREATSWORD)){
            this.consume();
            System.out.println("Durability remaining :" + this.durability);
            if (this.durability == 0){
                this.changeSwordType();
                System.out.println("Greatsword broke!! Changed to normal");
            }
        }
    }

    @Override
    public void deactivate(){
        this.getBounds().changeCollisionType(CollisionType.STUNNED);
    }

    @Override
    public int getSwordStrength() {
        return this.strength;
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public void setSwordStrength(int strength) {
        if (strength == NORMAL || strength == STRONG) {
            this.strength = strength;
        }
    }

    @Override
    public SwordType getSwordType() {
        return this.swordType;
    }

    @Override
    public void changeSwordType() {
        this.swordType = this.swordType.equals(SwordType.NORMAL) ? SwordType.GREATSWORD : SwordType.NORMAL;
        this.strength = this.swordType.equals(SwordType.GREATSWORD) ? STRONG : this.strength;
        if (this.swordType == SwordType.GREATSWORD) {
            this.setDurability(DURABILITY);
        }
    }

    /**
     * reduces the durability of the sword by one each time
     */
    private void consume() {
        this.durability--;
    }

}
