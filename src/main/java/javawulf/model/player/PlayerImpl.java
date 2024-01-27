package javawulf.model.player;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import javawulf.model.BoundingBox;
import javawulf.model.Direction;
import javawulf.model.Entity;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.powerUp.PowerUp;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.item.AmuletPiece;

public class PlayerImpl extends Entity implements Player {

    private static final int DAMAGE = -1;
    private static final int PLAYER_DEFAULT_SPEED = 1;
    private PlayerHealth health;
    private Score score;
    private Sword sword;
    private static final int NUMBER_OF_FRAGMENTS = 4;
    private List<AmuletPiece> fragmentsCollected;
    private Optional<PowerUp> activePowerUp;
    private PlayerColor color;

    public PlayerImpl(int startingX, int startingY, int health, int startingPoints){
        super(new CoordinateImpl(startingX, startingY), CollisionType.PLAYER, PLAYER_DEFAULT_SPEED);
        this.score = new ScoreImpl(startingPoints);
        this.setDirection(Direction.DOWN);
        this.health = new PlayerHealthImpl(health);
        this.sword = new SwordImpl(getPosition(), this.getDirection());
        this.fragmentsCollected  = new ArrayList<>(NUMBER_OF_FRAGMENTS);
        this.activePowerUp = Optional.empty();
        this.color = PlayerColor.NONE;
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
        this.setPosition(new CoordinateImpl(current.getX() + (int)(direction.getX()*delta),
            current.getY() + (int)(direction.getY()*delta)));
        this.getBounds().setCollisionArea(this.getPosition().getX(), this.getPosition().getY(), OBJECT_SIZE, OBJECT_SIZE);
        this.sword.move(this.getPosition(), direction, delta);
        this.setDirection(direction);
    }

    @Override
    public boolean isHit(BoundingBox box) {
        if (this.getBounds().isCollidingWith(box.getCollisionArea())
            && box.getCollisionType().equals(CollisionType.ENEMY)) {
            this.health.setHealth(DAMAGE);
            this.getBounds().changeCollisionType(CollisionType.STUNNED);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void collectAmuletPiece(AmuletPiece piece) throws IllegalStateException {
        if (this.fragmentsCollected.size()==NUMBER_OF_FRAGMENTS){
            throw new IllegalStateException("Already gotten all fragments of the amulet");
        } else {
            this.fragmentsCollected.add(piece);
        }
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
        return this.health.getHealth()<=0;
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
    public PlayerColor getColor() {
        return this.color;
    }

    @Override
    public void setColor(PlayerColor color) {
        this.color = color;
    }

    @Override
    public List<AmuletPiece> getFragments() {
        return this.fragmentsCollected;
    }
    
}
