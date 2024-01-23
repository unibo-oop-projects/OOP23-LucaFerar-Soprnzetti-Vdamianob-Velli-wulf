package javawulf.model;

public class GreatSword extends Collectable implements Item {

    public GreatSword(PositionOnMap position, Integer points, Integer duration) {
        super(position, points);
    }

    @Override
    public void applyEffect(Player p) {
        p.getSword().changeSwordType();
    }
}
