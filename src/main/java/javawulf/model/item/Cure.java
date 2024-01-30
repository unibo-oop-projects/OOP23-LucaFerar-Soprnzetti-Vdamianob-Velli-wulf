package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class Cure extends AbstractCollectable {
    
    private final static int POINTS = 100;

    public Cure(Coordinate position) {
        super(position, POINTS);
    }

    @Override
    public void applyEffect(Player p) {
        p.getPlayerHealth().setHealth(1);
    }

}
