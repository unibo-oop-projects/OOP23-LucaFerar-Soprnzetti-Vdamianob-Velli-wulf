package javawulf.model.enemy;

import javawulf.model.Entity;
import javawulf.model.map.Map;
import javawulf.model.player.Player;

public interface Enemy extends Entity {
    
    public void move(Player p, Map m);

    public void takeHit(Player p);
}
