package javawulf.model;

import java.util.Optional;

import javawulf.model.BoundingBox.CollisionType;

public class PlayerImpl extends Entity implements Player {

    private int health;
    private int score;
    private Sword sword;
    private int multiplier = 1;
    private Optional<PowerUp> activePowerUp;
    private int shield;
    private int maxHealth;

    public PlayerImpl(int startingX, int startingY, int health){
        //this.setPosition(new PositionOnMapImpl(startingX, startingY));
        super(new PositionOnMapImpl(startingX, startingY), CollisionType.PLAYER, 1);
        this.score = 0;
        this.health = health;
        this.maxHealth = health;
        this.sword = new SwordImpl(new PositionOnMapImpl(startingX, startingY+1));
        this.activePowerUp = Optional.empty();
    }

    @Override
    public void attack() {
        // TODO generate boundingbox in area considering its type
        this.sword.getSwordType();
        //this.sword.setBounds();
        //considering the player direction form the bounding box
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void move() throws IllegalStateException {
        // TODO Auto-generated method stub
        this.getSpeed();
        //this.setDirection(getDirection());
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public boolean isHit(BoundingBox b) {
        if (this.getBounds().isCollidingWith(b.getCollisionArea())
            && b.getCollisionType().equals(CollisionType.ENEMY)) {
            takeDamage();
            return true;
        } else {
            return false;
        }
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
        this.score = this.score + points*multiplier;    
    }

    @Override
    public Sword getSword() {
        return this.sword;
    }
    
}
