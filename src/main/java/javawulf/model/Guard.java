package javawulf.model;

public class Guard extends EnemyImpl {

    private Integer killValue;

    public Guard(BoundingBox collision, PositionOnMap position, Integer speed, BoundingBox hitBox, Integer points,
            Integer killValue) {
        super(collision, position, speed, hitBox, points);
        this.killValue = killValue;
    }

    public Integer getKillValue() {
        return this.killValue;
    }

    public boolean isKillable() {
        // TODO implement here
        return false;
    }

    public void checkRoom() {
        // TODO implement here
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void takeHit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'takeHit'");
    }
}
