package javawulf.model;

import javawulf.model.player.Player;

public class Cure extends Collectable implements Item {

    public Cure(PositionOnMap position, Integer points) {
        super(position, points);
    }

    @Override
    public void applyEffect(Player p) {
        p.getPlayerHealth().setHealth(1);
    }

}
