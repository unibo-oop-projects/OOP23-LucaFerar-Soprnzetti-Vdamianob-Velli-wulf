package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class Minimap extends AbstractCollectable {

    private final static int POINTS = 600;

    public Minimap(Coordinate position) {
        super(position, POINTS);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void applyEffect(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }

}
