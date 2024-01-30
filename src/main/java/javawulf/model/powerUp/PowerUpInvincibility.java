package javawulf.model.powerUp;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class PowerUpInvincibility extends PowerUpImpl {
    
    private final static int DURATION = 10;
    private final static int POINTS = 100;
    private final static String TYPE = "Invincibility";    
    
    public PowerUpInvincibility(Coordinate position) {
        super(position, POINTS, TYPE, DURATION);
    }

    @Override
    public void applyEffect(Player p) {
        if(this.stillActive()){
            //make the player invincible
        } else {
            //return the player to his normal form
        }
    }
    
}
