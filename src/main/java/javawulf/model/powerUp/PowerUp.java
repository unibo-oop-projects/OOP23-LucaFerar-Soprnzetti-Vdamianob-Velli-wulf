package javawulf.model.powerUp;

import javawulf.model.Collectable;

public interface PowerUp extends Collectable{

    boolean stillActive();

    void updateDuration();

}
