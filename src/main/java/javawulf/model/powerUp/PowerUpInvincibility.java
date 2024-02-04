package javawulf.model.powerUp;

import javawulf.model.Coordinate;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;

public class PowerUpInvincibility extends PowerUpImpl { 
    
    private static final int STUN_FINISHED = 0; 

    public PowerUpInvincibility(final Coordinate position, final int duration, final int points, final String type) {
        super(position, points, type, duration);
    }

    @Override
    public void applyEffect(Player p) {
        super.applyEffect(p);
        if(this.stillActive()) {
            //make the player invincible
            p.getBounds().changeCollisionType(CollisionType.STUNNED);
            p.setStun(this.getDuration());
        } else {
            //return the player to his normal form
            resetEffect(p);
        }
    }

    @Override
    public void resetEffect(Player p) {
        p.getBounds().changeCollisionType(CollisionType.PLAYER);
        p.setStun(STUN_FINISHED);
    }
    
    
}
