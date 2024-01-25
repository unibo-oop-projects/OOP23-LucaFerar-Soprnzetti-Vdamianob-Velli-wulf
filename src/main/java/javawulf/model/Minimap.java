package javawulf.model;

import javawulf.model.player.Player;

public class Minimap extends Collectable implements Item {

    public Minimap(PositionOnMap position, Integer points) {
        super(position, points);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void applyEffect(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }

}
