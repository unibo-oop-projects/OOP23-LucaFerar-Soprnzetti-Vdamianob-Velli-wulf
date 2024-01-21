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

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public void move() {

        if (System.currentTimeMillis() - this.moveTime >= timeToWait * 1000) {
            this.setDirection(Direction.values()[new Random().nextInt(4)]);
            this.moveTime = System.currentTimeMillis();
            this.timeToWait = new Random().nextInt(4) + 1;
        }

        switch (this.getDirection()) {
            case UP:
                this.getPosition().setPosition(this.getPosition().getX(), this.getPosition().getY() - this.getSpeed());
                break;
            case DOWN:
                this.getPosition().setPosition(this.getPosition().getX(), this.getPosition().getY() + this.getSpeed());
                break;
            case LEFT:
                this.getPosition().setPosition(this.getPosition().getX() - this.getSpeed(), this.getPosition().getY());
                break;
            case RIGHT:
                this.getPosition().setPosition(this.getPosition().getX() + this.getSpeed(), this.getPosition().getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void takeHit(Player p) {
        this.setAlive(false);
    }

}
