package javawulf.model.player;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import javawulf.model.BoundingBox;
import javawulf.model.Direction;
import javawulf.model.AbstractEntity;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.powerUp.PowerUp;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.item.AmuletPiece;

public class PlayerImpl extends AbstractEntity implements Player {

    private static final int DAMAGE = -1;
    private PlayerHealth health;
    private Score score;
    private Sword sword;
    private static final int NUMBER_OF_PIECES = 4;
    private List<AmuletPiece> piecesCollected;
    private Optional<PowerUp> activePowerUp;
    private PlayerColor color;

    public PlayerImpl(int startingX, int startingY, int health, int startingPoints) {
        super(new CoordinateImpl(startingX, startingY), CollisionType.PLAYER, Player.DEFAULT_SPEED);
        this.score = new ScoreImpl(startingPoints);
        this.setDirection(Direction.DOWN);
        this.health = new PlayerHealthImpl(health);
        this.sword = new SwordImpl(this.getPosition(), this.getDirection());
        this.piecesCollected  = new ArrayList<>(NUMBER_OF_PIECES);
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
        int delta = this.getSpeed() * MOVEMENT_DELTA;
        // var preview = this.getPosition();
        // preview.setPosition(current.getX() + (int)direction.getX()*delta,
        // current.getY() + (int)direction.getY()*delta);
        //if (this.isCollidingWithWall(null)) { // if wall, this will be changed later
        //    throw new IllegalStateException("There is a wall");
        //} // else {
          // this.setPosition(preview);
          // }
        this.setPosition(new CoordinateImpl(current.getX() + (int) (direction.getX() * delta),
                current.getY() + (int) (direction.getY() * delta)));
        this.getBounds().setCollisionArea(this.getPosition().getX(), this.getPosition().getY(), OBJECT_SIZE,
                OBJECT_SIZE);
        this.sword.move(this.getPosition(), direction);
        this.setDirection(direction);
    }

    @Override
    public boolean isHit(BoundingBox box) {
        if (this.getBounds().isCollidingWith(box.getCollisionArea())
            && box.getCollisionType().equals(CollisionType.ENEMY)
            && this.getBounds().getCollisionType().equals(CollisionType.PLAYER)) {
            this.health.setHealth(DAMAGE);
            this.getBounds().changeCollisionType(CollisionType.STUNNED);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void collectAmuletPiece(AmuletPiece piece) throws IllegalStateException {
        if (this.piecesCollected.size()==NUMBER_OF_PIECES){
            throw new IllegalStateException("Already gotten all fragments of the amulet");
        } else {
            this.piecesCollected.add(piece);
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
        return this.health.getHealth() <= 0;
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
    public List<AmuletPiece> getPieces() {
        return this.piecesCollected;
    }

}
