package javawulf.model.enemy;

import java.util.Random;

import javawulf.model.Direction;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class Pawn extends EnemyImpl {

    private boolean isAlive;
    private long moveTime;
    private int timeToWait;

    public Pawn(Coordinate position, Integer speed, int points) {
        super(position, speed, points);
        this.isAlive = true;
        this.moveTime = System.currentTimeMillis();
        this.timeToWait = new Random().nextInt(4) + 1;
        this.setDirection(Direction.values()[new Random().nextInt(4)]);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getTimeToWait() {
        return timeToWait;
    }

    @Override
    public void move(Player p) {

        if (System.currentTimeMillis() - this.moveTime >= timeToWait * 1000) {
            this.setDirection(Direction.values()[new Random().nextInt(4)]);
            this.moveTime = System.currentTimeMillis();
            this.timeToWait = new Random().nextInt(4) + 1;
        }

        double newX = this.getPosition().getX() + this.getDirection().getX() * this.getSpeed() * MOVEMENT_DELTA;
        double newY = this.getPosition().getY() + this.getDirection().getY() * this.getSpeed() * MOVEMENT_DELTA;

        this.getPosition().setPosition((int) Math.round(newX), (int) Math.round(newY));
        this.getBounds().setCollisionArea(this.getPosition().getX(), this.getPosition().getY(), OBJECT_SIZE,
                OBJECT_SIZE);
        /*
         * TODO: Fix collision detection
         * if (!this.getBounds().isCollidingWith(newX, newY)){
         * this.getPosition().setPosition(newX, newY);
         * }
         */
    }

    @Override
    public void takeHit(Player p) {
        if (this.isHit(p.getSword())) {
            this.isAlive = false;
            p.getScore().addPoints(this.getPoints());
        }
    }

}
