package javawulf.model.enemy;

import javawulf.model.Entity;
import javawulf.model.player.Player;

public interface Enemy extends Entity {
    
    public void move(Player p);

    public boolean inflictDamage(Player p);

    public void takeHit(Player p);
}
