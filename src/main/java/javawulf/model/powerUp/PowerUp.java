package javawulf.model.powerUp;

import javawulf.model.Collectable;
import javawulf.model.player.Player;

public interface PowerUp extends Collectable{
    
    public boolean stillActive();

    public void updateDuration();

    public int getPoints();

    public int getDuration();

    public String getType();
    
    public void finishEffect(Player p);

}
