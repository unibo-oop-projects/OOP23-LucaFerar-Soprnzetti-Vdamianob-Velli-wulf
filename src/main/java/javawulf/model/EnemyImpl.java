package javawulf.model;

public abstract class EnemyImpl extends Character implements Enemy {

    private Integer points;

    public EnemyImpl(BoundingBox collision, PositionOnMap position, Integer speed, BoundingBox hitBox, Integer points) {
        super(collision, position, speed, hitBox);
        this.points = points;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public abstract void move();

    public void inflictDamage(Player p) {
        if (this.isPlayerColliding(p)) {
            p.takeDamage();
        }
    }

    private boolean isPlayerColliding(Player p) {
        return this.getBounds().isCollidingWith(p.getBounds());
    }

    public abstract void takeHit();
}
