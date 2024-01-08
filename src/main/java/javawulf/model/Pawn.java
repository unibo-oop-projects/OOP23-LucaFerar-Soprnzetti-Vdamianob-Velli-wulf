package javawulf.model;

public class Pawn extends EnemyImpl {

    public Pawn(BoundingBox collision, PositionOnMap position, Integer speed, BoundingBox hitBox, Integer points) {
        super(collision, position, speed, hitBox, points);
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
