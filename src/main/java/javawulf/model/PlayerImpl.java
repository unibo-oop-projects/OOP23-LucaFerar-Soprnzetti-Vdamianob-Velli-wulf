package javawulf.model;

import java.util.Optional;

import javawulf.model.BoundingBox.CollisionType;

public class PlayerImpl extends Entity implements Player {

    private int health;
    private Score score;
    private Sword sword;
    private Optional<PowerUp> activePowerUp;
    private int shield;
    private int maxHealth;

    public PlayerImpl(int startingX, int startingY, int health){
        super(new PositionOnMapImpl(startingX, startingY), CollisionType.PLAYER, 1);
        this.score = new ScoreImpl();
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
    public Score getScore() {
        return this.score;
    }

    @Override
    public Sword getSword() {
        return this.sword;
    }
    
}
