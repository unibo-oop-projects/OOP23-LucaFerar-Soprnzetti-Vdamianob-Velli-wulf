package javawulf.model.powerUp;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class PowerUpInvincibility extends PowerUpImpl { 
    
    public PowerUpInvincibility(final Coordinate position, final int duration, final int points, final String type) {
        super(position, points, type, duration);
    }

    @Override
    public void applyEffect(Player p) {
        if(this.stillActive()) {
            //make the player invincible
        } else {
            //return the player to his normal form
        }
    }
    
}
