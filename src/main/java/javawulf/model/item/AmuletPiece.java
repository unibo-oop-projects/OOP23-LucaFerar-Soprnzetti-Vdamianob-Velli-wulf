package javawulf.model.item;

import javawulf.model.Collectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class AmuletPiece extends Collectable implements Item {

    public AmuletPiece(Coordinate position, Integer points) {
        super(position, points);
    }

    @Override
    public void applyEffect(Player p) {
        p.collectAmuletPiece(this);
    }

}
