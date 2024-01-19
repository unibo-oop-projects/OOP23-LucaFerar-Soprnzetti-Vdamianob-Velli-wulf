package javawulf.model;

public class PlayerImpl extends Entity implements Player {

    public enum SwordType{
        NORMAL,
        GREATSWORD;
    }

    private int health;
    private SwordType sword;
    private int score;
    private int strength;

    @Override
    public void attack() {
        // TODO generate boundingbox in area considering its type
        SwordType type = getSwordType();
        //considering the player direction form the bounding box
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public SwordType getSwordType(){
        return this.sword;
    }

    @Override
    public void move() throws IllegalStateException {
        // TODO Auto-generated method stub
        super.getSpeed();
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public boolean isHit() {
        //if (condition) {
            takeDamage();
        //then return true;} else
        return false;
    }

    private void takeDamage(){
        this.health--;
        System.out.println("Health remaining :" + getHealth());
    }

    @Override
    public void changeSwordType() {
        this.sword = this.sword==SwordType.NORMAL ? SwordType.GREATSWORD : SwordType.NORMAL;
        this.strength = this.sword==SwordType.GREATSWORD ? 2 : 1;
    }

    @Override
    public boolean isAmuletPieceInCoordinate() {
        return false;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void usePowerUp(PowerUp p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usePowerUp'");
    }

    @Override
    public boolean isDefeated() {
        return this.health==0;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void increaseScore(int points) {
        this.score = this.score + points;    
    }
    
}
