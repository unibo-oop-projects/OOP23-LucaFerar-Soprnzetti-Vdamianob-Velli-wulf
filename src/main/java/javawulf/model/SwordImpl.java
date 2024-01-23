package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;

public class SwordImpl extends GameObject implements Sword {

    private final static int NORMAL = 1;
    private final static int STRONG = 2;
    private int strength;
    private SwordType swordType;

    public SwordImpl(PositionOnMap position){
        super(position, new BoundingBoxImpl(0, 0, 0, 0, CollisionType.SWORD));
        this.strength = NORMAL;
        this.swordType = SwordType.GREATSWORD;
    }

    @Override
    public int getSwordStrength() {
        return this.strength;
    }

    @Override
    public void setSwordStrength(int strength) {
        if (strength==NORMAL || strength==STRONG){
            this.strength = strength;
        }
    }

    @Override
    public SwordType getSwordType() {
        return this.swordType;
    }

    @Override
    public void changeSwordType() {
        this.swordType = this.swordType==SwordType.NORMAL ? SwordType.GREATSWORD : SwordType.NORMAL;
        this.strength = this.swordType==SwordType.GREATSWORD ? STRONG : this.strength;
    }
    
}
