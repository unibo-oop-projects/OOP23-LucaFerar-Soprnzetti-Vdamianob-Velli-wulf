package javawulf.model;

public interface Sword extends GameElement {
    
    public enum SwordType{
        NORMAL,
        GREATSWORD;
    }
    
    public int getSwordStrength();

    public int getDurability();

    public void setDurability(int durability);

    public void setSwordStrength(int strength);

    public void consume();

    public SwordType getSwordType();

    public void changeSwordType();

}
