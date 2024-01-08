package javawulf.model;

public interface Enemy extends GameElement {
    public void move();

    public void inflictDamage(Player p);

    public void takeHit();
}
