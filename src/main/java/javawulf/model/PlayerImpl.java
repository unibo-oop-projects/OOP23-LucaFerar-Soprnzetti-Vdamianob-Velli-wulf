package javawulf.model;

import java.util.Optional;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.PlayerHealth.ShieldStatus;

public class PlayerImpl extends Entity implements Player {

    private static final int DAMAGE = -1;
    private PlayerHealth health;
    private Score score;
    private Sword sword;
    private Optional<PowerUp> activePowerUp;

    public PlayerImpl(int startingX, int startingY, int health){
        super(new PositionOnMapImpl(startingX, startingY), CollisionType.PLAYER, 1);
        this.score = new ScoreImpl();
        this.health = new PlayerHealthImpl(health);
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
        if(this.health.getShieldStatus().equals(ShieldStatus.NONE)){
            this.health.setHealth(DAMAGE);
        } else {
            if (this.health.getShieldStatus().equals(ShieldStatus.FULL)){
                this.health.setShieldStatus(ShieldStatus.HALF);
            } else {
                this.health.setShieldStatus(ShieldStatus.NONE);
            }
        }
        System.out.println("Health remaining :" + this.getPlayerHealth().getHealth());
        System.out.println("Shield hits remaining :" + this.getPlayerHealth().getShieldStatus().strength);
    }

    @Override
    public boolean isAmuletPieceInCoordinate() {
        return false;
    }

    @Override
    public PlayerHealth getPlayerHealth() {
        return this.health;
    }

    @Override
    public void usePowerUp(PowerUp p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usePowerUp'");
    }

    @Override
    public boolean isDefeated() {
        return this.health.getHealth()==0;
    }

    @Override
    public Score getScore() {
        return this.score;
    }

    @Override
    public Sword getSword() {
        return this.sword;
    }

    @Override
    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public void setSword(Sword sword) {
        this.sword = sword;
    }

    @Override
    public void setPlayerHealth(PlayerHealth health) {
        this.health = health;
    }
    
}
