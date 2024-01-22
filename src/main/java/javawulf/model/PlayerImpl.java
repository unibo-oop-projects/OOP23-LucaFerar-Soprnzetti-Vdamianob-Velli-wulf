package javawulf.model;

public class PlayerImpl extends Entity implements Player {

    private int health;
    private int score;
    private Sword sword;

    @Override
    public void attack() {
        // TODO generate boundingbox in area considering its type
        //considering the player direction form the bounding box
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
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

    @Override
    public Sword getSword() {
        return this.sword;
    }
    
}
