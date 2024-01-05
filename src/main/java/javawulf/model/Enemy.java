package javawulf.model;

public interface Enemy {
    public void move();

    public void inflictDamage(Player p);

    public void takeHit();
}
