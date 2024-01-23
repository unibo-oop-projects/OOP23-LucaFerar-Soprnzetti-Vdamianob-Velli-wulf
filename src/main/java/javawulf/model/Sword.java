package javawulf.model;

public interface Sword extends GameElement {
    
    public enum SwordType{
        NORMAL,
        GREATSWORD;
    }
    
    public int getSwordStrength();

    public int getDurability();

    public void setDurability(int durability);

    /**
     * changes the strength of the sword indipedently from the type of the sword, 
     * this is used when the player picks up a powerup that increases his attack
     */
    public void setSwordStrength(int strength);

    /**
     * returns the type of the sword the player currently has
     */
    public SwordType getSwordType();

    /**
     * changes the type of the sword, from normal to greatsword and viceversa
     */
    public void changeSwordType();

    /**
     * reduces the durability of the sword by one each time
     */
    public void consume();

}
