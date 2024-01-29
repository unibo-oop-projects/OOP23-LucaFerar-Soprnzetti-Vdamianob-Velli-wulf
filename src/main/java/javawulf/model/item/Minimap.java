package javawulf.model.item;

import javawulf.model.Collectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class Minimap extends Collectable implements Item {

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
