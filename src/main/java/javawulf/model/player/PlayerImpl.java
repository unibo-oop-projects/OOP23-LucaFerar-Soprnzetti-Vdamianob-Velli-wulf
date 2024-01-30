package javawulf.model.player;

import java.util.List;
import java.util.ArrayList;

import javawulf.model.BoundingBox;
import javawulf.model.Direction;
import javawulf.model.AbstractEntity;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.powerUp.PowerUpHandler;
import javawulf.model.powerUp.PowerUpHandlerImpl;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.item.AmuletPiece;

/**
 * PlayerImpl is an implementation of Player
 */
public final class PlayerImpl extends AbstractEntity implements Player {

    private static final int DAMAGE = -1;
    private PlayerHealth health;
    private Score score;
    private Sword sword;
    private static final int NUMBER_OF_PIECES = 4;
    private List<AmuletPiece> piecesCollected;
    private PlayerColor color;
    private PowerUpHandler powerUpHandler;
    private static final int PLAYER_STUN = 4;

    /**
     * Creates a new Player
     * 
     * @param startingX The starting position on the X axis of the Player
     * @param startingY The starting position on the Y axis of the Player
     * @param health The amount of healtg the player starts the game with
     * @param startingPoints The amount of points the player starts the game with
     */
    public PlayerImpl(final int startingX, final int startingY, final int health, final int startingPoints) {
        super(new CoordinateImpl(startingX, startingY), CollisionType.PLAYER, Player.DEFAULT_SPEED);
        this.score = new ScoreImpl(startingPoints);
        this.setDirection(Direction.DOWN);
        this.health = new PlayerHealthImpl(health);
        this.sword = new SwordImpl(this.getPosition(), this.getDirection());
        this.piecesCollected  = new ArrayList<>(NUMBER_OF_PIECES);
        this.powerUpHandler = new PowerUpHandlerImpl();
        this.color = PlayerColor.NONE;
    }

    @Override
    public void attack() {
        this.sword.activate();
    }

    @Override
    public void move(final Direction direction) throws IllegalStateException {
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
    public boolean isHit(final BoundingBox box) {
        if (super.isHit(box)) {
            this.health.setHealth(DAMAGE);
            if (isDefeated()) {
                this.getBounds().changeCollisionType(CollisionType.INACTIVE);
                System.out.println("Oh no! You Died. GAME OVER");
            } else {
                this.getBounds().changeCollisionType(CollisionType.STUNNED);
                this.setStun(PLAYER_STUN);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void collectAmuletPiece(final AmuletPiece piece) throws IllegalStateException {
        if (this.piecesCollected.size() == NUMBER_OF_PIECES) {
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
    public PowerUpHandler getPowerUpHandler() {
        return this.powerUpHandler;
    }

    private boolean isDefeated() {
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
    public void setColor(final PlayerColor color) {
        this.color = color;
    }

    @Override
    public List<AmuletPiece> getPieces() {
        return this.piecesCollected;
    }

    @Override
    protected boolean control(final BoundingBox box) {
        return box.getCollisionType().equals(CollisionType.ENEMY)
            && this.getBounds().getCollisionType().equals(CollisionType.PLAYER);
    }

    @Override
    protected CollisionType originalCollisonType() {
        return CollisionType.PLAYER;
    }

}
