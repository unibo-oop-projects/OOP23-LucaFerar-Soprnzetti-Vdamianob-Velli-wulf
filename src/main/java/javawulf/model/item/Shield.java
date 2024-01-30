package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Collectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerHealth.ShieldStatus;

public class Shield extends AbstractCollectable implements Collectable {

    private final static int POINTS = 800;

    public Shield(Coordinate position) {
        super(position, POINTS);
    }

    @Override
    public void applyEffect(Player p) {
        p.getPlayerHealth().setShieldStatus(ShieldStatus.FULL);
    }

}
