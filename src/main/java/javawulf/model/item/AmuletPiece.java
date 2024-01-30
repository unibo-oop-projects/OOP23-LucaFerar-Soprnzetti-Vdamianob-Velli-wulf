package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class AmuletPiece extends AbstractCollectable {

    public enum Alignment {
        HORIZONTAL,
        VERTICAL,
        NONE
    }
    
    private final static int POINTS = 500;

    public AmuletPiece(Coordinate position) {
        super(position, POINTS);
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
