package javawulf.model;

public class Pawn extends EnemyImpl {

    private boolean isAlive;

    public Pawn(BoundingBox collision, PositionOnMap position, Integer speed, BoundingBox hitBox, Integer points) {
        super(collision, position, speed, hitBox, points);
        this.isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void takeHit() {
        this.setAlive(false);
    }

}
