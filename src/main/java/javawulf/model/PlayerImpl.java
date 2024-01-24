package javawulf.model;

import java.util.Optional;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.Sword.SwordType;

public class PlayerImpl extends Entity implements Player {

    private static final int DAMAGE = -1;
    private PlayerHealth health;
    private Score score;
    private Sword sword;
    private Optional<PowerUp> activePowerUp;

    public PlayerImpl(int startingX, int startingY, int health, int startingPoints){
        super(new PositionOnMapImpl(startingX, startingY), CollisionType.PLAYER, 1);
        this.score = new ScoreImpl(startingPoints);
        this.health = new PlayerHealthImpl(health);
        this.sword = new SwordImpl(new PositionOnMapImpl(startingX, startingY+1));
        this.activePowerUp = Optional.empty();
    }

    @Override
    public void attack() {
        // TODO generate boundingbox in area considering its type
        
        //implementation of the greatsword getting consumed
        if (this.sword.getSwordType().equals(SwordType.GREATSWORD)){
            
            this.sword.consume();

            System.out.println("Durability remaining :" + this.sword.getDurability());

            if (this.sword.getDurability()==0){
                this.sword.changeSwordType();
                System.out.println("Greatsword broke!! Changed to normal");
            }
            
        }

        //this.sword.setBounds();
        //considering the player direction form the bounding box
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void move(Direction direction) throws IllegalStateException {
        PositionOnMap current = this.getPosition();
        int delta = this.getSpeed(); //it will be multiplied by a constant, corresponding to the side of a tile/size of player
        this.getPosition().setPosition(current.getX() + (int)direction.getX()*delta,
            current.getY() + (int)direction.getY()*delta);
        //this.sword.setPosition(player.position, player.direction); //in sword also the direction will be changed
        //new Point(DAMAGE, DAMAGE).move(DAMAGE, DAMAGE);
        this.setDirection(direction);
        if(isDefeated()){ //if wall, this will be changed later
            throw new IllegalStateException("There is a wall");
        }
    }

    @Override
    public boolean isHit(BoundingBox b) {
        if (this.getBounds().isCollidingWith(b.getCollisionArea())
            && b.getCollisionType().equals(CollisionType.ENEMY)) {
            this.health.setHealth(DAMAGE);
            return true;
        } else {
            return false;
        }
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
