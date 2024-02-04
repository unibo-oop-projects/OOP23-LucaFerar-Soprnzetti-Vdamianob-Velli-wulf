package javawulf.model.powerUp;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;
import javawulf.model.player.Sword;
import javawulf.model.player.SwordImpl;

public class PowerUpAttack extends PowerUpImpl {
    
    public PowerUpAttack(final Coordinate position, final int duration, final int points, final String type) {
        super(position, points, type, duration);
    }

    @Override
    public void applyEffect(final Player p) {
        super.applyEffect(p);
        //the damage of the player gets increased only if he isnt using the great sword
        if(p.getSword().getSwordType() == Sword.SwordType.NORMAL && this.stillActive()) {
            //here i need to increment the damage of the player 
            p.getSword().setSwordStrength(SwordImpl.STRONG);
        } else {
            //here i need to make the damage return to normal
            this.resetEffect(p);
        }
    }

    @Override
    public void resetEffect(final Player p) {
        p.getSword().setSwordStrength(SwordImpl.NORMAL);
    }

    
    
}
