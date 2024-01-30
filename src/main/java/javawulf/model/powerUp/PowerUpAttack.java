package javawulf.model.powerUp;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;
import javawulf.model.player.Sword;

public class PowerUpAttack extends PowerUpImpl {
    
    private final static int DURATIONMILLI = 20_000;
    private final static int POINTS = 50;
    private final static String TYPE = "Attack";
    
    public PowerUpAttack(Coordinate position) {
        super(position, POINTS, TYPE, DURATIONMILLI);
    }

    @Override
    public void applyEffect(Player p) {
        //the damage of the player gets increased only if he isnt using the great sword
        if(p.getSword().getSwordType() == Sword.SwordType.NORMAL && this.stillActive()) {
            //here i need to increment the damage of the player 
        }
        else {
            //here i need to make the damage return to normal
        }
    }
}
