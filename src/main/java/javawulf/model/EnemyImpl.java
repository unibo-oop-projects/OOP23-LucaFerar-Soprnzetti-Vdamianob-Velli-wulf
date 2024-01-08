package javawulf.model;

public abstract class EnemyImpl extends Character implements Enemy {
    
    private PositionOnMap position;
    private Integer points;

    public PositionOnMap getPosition() {
        return this.position;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPosition(PositionOnMap position) {
        this.position = position;
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
