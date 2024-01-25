package javawulf.model.item;

import javawulf.model.Collectable;
import javawulf.model.PositionOnMap;
import javawulf.model.player.Player;

public class ExtraHeart extends Collectable implements Item {

    public ExtraHeart(PositionOnMap position, Integer points) {
        super(position, points);
    }

    @Override
    public void applyEffect(Player p) {
        p.getPlayerHealth().increaseMaxHealth(1);
    }

}
