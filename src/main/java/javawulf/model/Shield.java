package javawulf.model;

public class Shield extends Collectable implements Item {

    private int duration;

    public Shield(PositionOnMap position, Integer points, Integer duration) {
        super(position, points);
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
    }

    public void consume() {
        this.duration--;
    }

    @Override
    public void applyEffect(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }

}
