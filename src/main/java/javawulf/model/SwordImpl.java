package javawulf.model;

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
     * @param position the exact position the sword must be in when it is created
     */
    public SwordImpl(PositionOnMap position) {
        super(position, new BoundingBoxImpl(position.getX(), position.getY(), 0, 0, CollisionType.STUNNED));
        this.strength = NORMAL;
        this.swordType = SwordType.NORMAL;
    }

    @Override
    public void move(PositionOnMap playerPosition, Direction playerDirection, int delta){
        checkIfDiagonal(playerDirection);
        this.getPosition().setPosition(playerPosition.getX() + (int)this.swordDirection.getX()*delta,
            playerPosition.getY() + (int)this.swordDirection.getY()*delta);
    }

    private void checkIfDiagonal(Direction playerDirection){
        Direction movementDirection = playerDirection;
        if (playerDirection.equals(Direction.DOWN_LEFT) || playerDirection.equals(Direction.DOWN_RIGHT) ||
            playerDirection.equals(Direction.UP_LEFT) || playerDirection.equals(Direction.UP_RIGHT)){
                movementDirection = swordDirection;
        }
        this.swordDirection = movementDirection;
    }

    @Override
    public void activate(){
        this.getBounds().changeCollisionType(CollisionType.SWORD);
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

    @Override
    public void consume() {
        this.durability--;
    }

}
