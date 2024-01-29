package javawulf.model.item;

import javawulf.model.Collectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class AmuletPiece extends Collectable implements Item {

    public enum Alignment {
        HORIZONTAL,
        VERTICAL,
        NONE
    }

    public AmuletPiece(Coordinate position, Integer points) {
        super(position, points);
    }

    @Override
    public void applyEffect(Player p) {
        p.collectAmuletPiece(this);
    }

    public Alignment isPlayerAligned(Player p) {
        if (p.getPosition().getX() == this.getPosition().getX()) {
            return Alignment.HORIZONTAL;
        } else if (p.getPosition().getY() == this.getPosition().getY()) {
            return Alignment.VERTICAL;
        } else {
            return Alignment.NONE;
        }
    }

}
