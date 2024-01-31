package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

/**
 * The Minimap item reveals the map so that the player can orientate.
 */
public final class Minimap extends AbstractCollectable {

    private static final int POINTS = 600;

    /**
     * Creates a new minimap.
     * 
     * @param position the position of the minimap
     */
    public Minimap(final Coordinate position) {
        super(position, POINTS);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void applyEffect(final Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }

}
