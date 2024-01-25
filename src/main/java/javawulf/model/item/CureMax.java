package javawulf.model.item;

import javawulf.model.PositionOnMap;
import javawulf.model.player.Player;

public class CureMax extends Cure {

    public CureMax(PositionOnMap position, Integer points) {
        super(position, points);
    }

    @Override
    public void applyEffect(Player p) {
        p.getPlayerHealth().setHealth(p.getPlayerHealth().getMaxHealth());
    }
    
}
