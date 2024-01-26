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

    @Override
    public void move(Player p) {

        if (System.currentTimeMillis() - this.moveTime >= timeToWait * 1000) {
            this.setDirection(Direction.values()[new Random().nextInt(4)]);
            this.moveTime = System.currentTimeMillis();
            this.timeToWait = new Random().nextInt(4) + 1;
        }

        double newX = this.getPosition().getX() + this.getDirection().getX() * this.getSpeed();
        double newY = this.getPosition().getY() + this.getDirection().getY() * this.getSpeed();

        this.getPosition().setPosition((int) Math.round(newX), (int) Math.round(newY));
        /*
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
