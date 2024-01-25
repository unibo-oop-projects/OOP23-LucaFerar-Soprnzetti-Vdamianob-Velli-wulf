package javawulf.model.item;

import javawulf.model.Collectable;
import javawulf.model.PositionOnMap;
import javawulf.model.player.Player;

public class GreatSword extends Collectable implements Item {

    public GreatSword(PositionOnMap position, Integer points, Integer duration) {
        super(position, points);
    }

    @Override
    public void applyEffect(Player p) {
        p.getSword().changeSwordType();
    }
}
