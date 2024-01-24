package javawulf.model;

import java.util.Random;

public class Pawn extends EnemyImpl {

    private boolean isAlive;
    private long moveTime;
    private int timeToWait;

    public Pawn(BoundingBox collision, PositionOnMap position, Integer speed, BoundingBox hitBox, Integer points) {
        super(collision, position, speed, hitBox, points);
        this.isAlive = true;
        this.moveTime = System.currentTimeMillis();
        this.timeToWait = new Random().nextInt(4) + 1;
        this.setDirection(Direction.values()[new Random().nextInt(4)]);
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void move() {

        if (System.currentTimeMillis() - this.moveTime >= timeToWait * 1000) {
            this.setDirection(Direction.values()[new Random().nextInt(4)]);
            this.moveTime = System.currentTimeMillis();
            this.timeToWait = new Random().nextInt(4) + 1;
        }

        int newX = this.getPosition().getX();
        int newY = this.getPosition().getY();

        switch (this.getDirection()) {
            case UP:
                newY -= this.getSpeed();
                break;
            case DOWN:
                newY += this.getSpeed();
                break;
            case LEFT:
                newX -= this.getSpeed();
                break;
            case RIGHT:
                newX += this.getSpeed();
                break;
            default:
                break;
        }
        if (!this.getBounds().isCollidingWith(newX, newY)){
            this.getPosition().setPosition(newX, newY);
        }
    }

    @Override
    public void takeHit(Player p) {
        this.isAlive = false;
        p.increaseScore(this.getPoints());
    }

}
