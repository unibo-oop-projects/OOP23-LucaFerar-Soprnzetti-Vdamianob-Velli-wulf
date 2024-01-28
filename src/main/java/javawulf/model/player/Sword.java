package javawulf.model.player;

import javawulf.model.Direction;
import javawulf.model.GameElement;
import javawulf.model.Coordinate;

/**
 * Sword is class whose purpose is to deal with all of the attack functions of player,
 * which are all linked to the player character's sword and its statistics
 */
public interface Sword extends GameElement {
    
    /**
     * SwordType defines the current type of sword. By default is is NORMAL
     */
    public enum SwordType{
        NORMAL,
        GREATSWORD;
    }

    /**
     * Updates the position and direction of the Sword according to the player position
     * and movement delta
     * 
     * @param playerPosition The current position of player
     * @param playerDirection The direction the player is currently facing
     */
    public void move(Coordinate playerPosition, Direction playerDirection);

    /**
     * Activates the collision of the Sword allowing the player to attack
     */
    public void activate();

    /**
     * Deactivates the collsion of the Sword
     */
    public void deactivate();

    /**
     * @return The current strength of the player's sword
     */
    public int getSwordStrength();

    public int getDurability();

    public void setDurability(int durability);

    /**
     * Set the sword strength. It is used both when the sword type is GREATSWORD
     * and when a strength boosting power-up is obtained
     * 
     * @param strength The strength the sword changes into
     */
    public void setSwordStrength(int strength);

    /**
     * @return The current type of the player's sword
     */
    public SwordType getSwordType();

    /**
     * Changes the sword's type automatically
     */
    public void changeSwordType();

}
