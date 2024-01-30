package javawulf.model.item;

import javawulf.model.Collectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class ExtraHeart extends Collectable implements Item {
    
    private final static int POINTS = 400;

    public ExtraHeart(Coordinate position) {
        super(position, POINTS);
    }

    @Override
    public void applyEffect(Player p) {
        p.getPlayerHealth().increaseMaxHealth(1);
    }

}
