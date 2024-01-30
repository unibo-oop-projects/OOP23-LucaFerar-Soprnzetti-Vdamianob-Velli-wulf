package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Collectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class ExtraHeart extends AbstractCollectable implements Collectable {
    
    private final static int POINTS = 400;

    public ExtraHeart(Coordinate position) {
        super(position, POINTS);
    }

    @Override
    public void applyEffect(Player p) {
        p.getPlayerHealth().increaseMaxHealth(1);
    }

}
