package javawulf.model;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;

public abstract class Collectable extends GameObject {

    private final int points;

    public Collectable(Coordinate position, int points) {
        super(position, CollisionType.COLLECTABLE);
        this.points = points;
    }
    
    public int getPoints() {
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