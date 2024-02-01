package javawulf.model.powerUp;

import javawulf.model.Collectable;

public interface PowerUp extends Collectable{

    public boolean stillActive();

    public void updateDuration();

    public int getPoints();

    public int getDuration();

    public String getType();
    
}
