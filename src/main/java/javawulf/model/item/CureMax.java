package javawulf.model.item;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class CureMax extends Cure {

    public CureMax(Coordinate position, Integer points) {
        super(position, points);
    }

    @Override
    public void applyEffect(Player p) {
        p.getPlayerHealth().setHealth(p.getPlayerHealth().getMaxHealth());
    }
    
}
