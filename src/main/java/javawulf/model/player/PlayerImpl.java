package javawulf.model.player;

import java.util.Optional;

import javawulf.model.BoundingBox;
import javawulf.model.Direction;
import javawulf.model.Entity;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.powerUp.PowerUp;
import javawulf.model.BoundingBox.CollisionType;

public class PlayerImpl extends Entity implements Player {

    private static final int PLAYER_SIZE = 24;
    private static final int MOVEMENT_DELTA = PLAYER_SIZE/8;
    private static final int DAMAGE = -1;
    private PlayerHealth health;
    private Score score;
    private Sword sword;
    private Optional<PowerUp> activePowerUp;

    public PlayerImpl(int startingX, int startingY, int health, int startingPoints){
        super(new CoordinateImpl(startingX, startingY), CollisionType.PLAYER, 1);
        this.score = new ScoreImpl(startingPoints);
        this.setDirection(Direction.DOWN);
        this.health = new PlayerHealthImpl(health);
        this.sword = new SwordImpl(getPosition(), this.getDirection());
        this.activePowerUp = Optional.empty();
    }

    @Override
    public void attack() {
        this.sword.activate();
    }

    @Override
    public void move(Direction direction) throws IllegalStateException {
        Coordinate current = this.getPosition();
        int delta = this.getSpeed()*MOVEMENT_DELTA;
        // var preview = this.getPosition();
        // preview.setPosition(current.getX() + (int)direction.getX()*delta,
        //     current.getY() + (int)direction.getY()*delta);
        if(isDefeated()){ //if wall, this will be changed later
             throw new IllegalStateException("There is a wall");
        }// else {
        //     this.setPosition(preview);
        // }
        this.getPosition().setPosition(current.getX() + (int)direction.getX()*delta,
            current.getY() + (int)direction.getY()*delta);
        this.getBounds().setCollisionArea(this.getPosition().getX(), this.getPosition().getY(), PLAYER_SIZE, PLAYER_SIZE);
        this.sword.move(this.getPosition(), direction, delta);
        this.setDirection(direction);
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
