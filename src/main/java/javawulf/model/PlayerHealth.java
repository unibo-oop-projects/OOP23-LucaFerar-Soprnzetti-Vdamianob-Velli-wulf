package javawulf.model;

public interface PlayerHealth {
    public enum ShieldStatus{
        NONE(0),
        HALF(1),
        FULL(2);

        public final int strength;

        ShieldStatus(int strength){
            this.strength = strength;
        }

        public int getStrength(){
            return this.strength;
        }
    }

    public int getHealth();

    public int getMaxHealth();

    public ShieldStatus getShieldStatus();

    public void setHealth(int health);

    public void increaseMaxHealth(int increase);

    public void setShieldStatus(ShieldStatus status);
    
}
