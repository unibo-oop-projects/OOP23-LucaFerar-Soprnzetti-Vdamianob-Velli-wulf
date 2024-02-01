package javawulf.model.enemy;

import java.util.Random;

import javawulf.model.Direction;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.map.Map;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.player.Player;

/**
 * The pawn represents the most basic enemy in the game, it
 * moves randomly around the map.
 */
public final class Pawn extends EnemyImpl {

    private static final int POINTS = 100;

    private final Random random = new Random();

    private boolean isAlive;
    private int timeToWait;
    private int tickCount;

    /**
     * Creates a pawn.
     * 
     * @param position the position of the pawn when created
     */
    public Pawn(final Coordinate position) {
        super(position);
        this.isAlive = true;
        this.timeToWait = random.nextInt(4) + 1;
        this.setDirection(Direction.values()[random.nextInt(4)]);
        this.tickCount = 0;
    }

    /**
     * @return true if the pawn is alive, false otherwise
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * @return the time to wait before changing direction
     */
    public int getTimeToWait() {
        return timeToWait;
    }

    /**
     * @return the tick count
     */
    public int getTickCount() {
        return this.tickCount;
    }

    @Override
    public void move(final Player p, final Map m) {

        int delta = this.getSpeed() * MOVEMENT_DELTA;

        int newX = this.getPosition().getX() + (int) (this.getDirection().getX() * delta);
        int newY = this.getPosition().getY() + (int) (this.getDirection().getY() * delta);

        this.setPosition(new CoordinateImpl(newX, newY));
        this.getBounds().setCollisionArea(this.getPosition().getX(),
                this.getPosition().getY(), OBJECT_SIZE, OBJECT_SIZE);

        if (this.isCollidingWithWall(m)) {
            Direction actualDirection = this.getDirection();
            do {
                this.setDirection(Direction.values()[random.nextInt(4)]);
            } while (actualDirection.equals(this.getDirection()));
        }
    }

    @Override
    public void takeHit(final Player p) {
        if (super.isHit(p.getSword().getBounds())) {
            this.isAlive = false;
            p.getScore().addPoints(POINTS);
            this.getBounds().changeCollisionType(CollisionType.INACTIVE);
        }
    }

    @Override
    public void tick() {

        this.tickCount++;
        Direction directionToChange = this.getDirection();

        if (this.tickCount >= this.timeToWait) {
            do {
                this.setDirection(Direction.values()[random.nextInt(4)]);
            } while (directionToChange.equals(this.getDirection()));
            this.timeToWait = random.nextInt(4) + 1;
            this.tickCount = 0;
        }
    }

}
