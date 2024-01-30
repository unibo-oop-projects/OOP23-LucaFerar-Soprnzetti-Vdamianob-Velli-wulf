package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class CureMax extends AbstractCollectable {

    private final static int POINTS = 400;

    public CureMax(Coordinate position) {
        super(position, POINTS);
    }

    @Override
    public void applyEffect(Player p) {
        p.getPlayerHealth().setHealth(p.getPlayerHealth().getMaxHealth());
    }

}
