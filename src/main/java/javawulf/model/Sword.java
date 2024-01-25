package javawulf.model;

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
    
    public void move(PositionOnMap playerPosition, Direction playerDirection, int delta);

    public void activate();

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

    /**
     * reduces the durability of the sword by one each time
     */
    public void consume();

}
