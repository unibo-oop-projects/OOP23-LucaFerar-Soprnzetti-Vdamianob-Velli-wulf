package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;

public abstract class Collectable extends GameObject {

    private final Integer points;

    public Collectable(PositionOnMap position, Integer points) {
        super(position, CollisionType.COLLECTABLE);
        this.points = points;
    }
    
    public Integer getPoints() {
        return this.points;
    }
  
    public void collect(Player p){
        if (this.getBounds().isCollidingWith(p.getBounds().getCollisionArea())) {
            this.applyEffect(p);
            p.getScore().addPoints(this.points);
        }
    };

    public abstract void applyEffect(Player p);

}