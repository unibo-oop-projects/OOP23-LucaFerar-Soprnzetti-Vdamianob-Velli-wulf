package javawulf.model;

public class Shield extends Collectable implements Item {

    private Integer duration;

    public Shield(PositionOnMap position, Integer points, Integer duration) {
        super(position, points);
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public void applyEffect() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }

}
