package javawulf.model.powerUp;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class PowerUpInvincibility extends PowerUpImpl {
    
    private final int DURATIONMILLI = 10_000;
    private final int POINTS = 100;
    private final String TYPE = "Invincibility";    
    
    public PowerUpInvincibility(Coordinate position, Integer points, String type, int durationInMilli) {
        super(position, points, type, durationInMilli);
        //need to put all constants in the factory
    }
    
        @Override
        public void applyEffect(Player player) {
            super.applyEffect(player);
            //need to make player invincible
        }
}
