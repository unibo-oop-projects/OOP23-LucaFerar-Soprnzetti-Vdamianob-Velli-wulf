package javawulf.model.item;

import javawulf.model.Collectable;
import javawulf.model.PositionOnMap;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerHealth.ShieldStatus;

public class Shield extends Collectable implements Item {

    public Shield(PositionOnMap position, Integer points) {
        super(position, points);
    }

    @Override
    public void applyEffect(Player p) {
        p.getPlayerHealth().setShieldStatus(ShieldStatus.FULL);
    }

}
