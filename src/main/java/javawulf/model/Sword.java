package javawulf.model;

public interface Sword extends GameElement {
    
    public enum SwordType{
        NORMAL,
        GREATSWORD;
    }
    
    public int getSwordStrength();

    public void setSwordStrength(int strength);

    public SwordType getSwordType();

    public void changeSwordType();

}
