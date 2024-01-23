package javawulf.model;

public class PlayerHealthImpl implements PlayerHealth {

    private int health;
    private int maxHealth;
    private ShieldStatus shieldStatus;

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public ShieldStatus getShieldStatus() {
        return this.shieldStatus;
    }

    @Override
    public void setHealth(int health) {
        if (health>=0){
            if (this.health + health < this.maxHealth) {
                this.health = this.health + health;
            } else {
                this.health = this.maxHealth;
            }
        } else {
            this.health--;
        }
    }

    @Override
    public void increaseMaxHealth(int increase) {
        this.maxHealth = this.maxHealth + increase;
    }

    @Override
    public void setShieldStatus(ShieldStatus status) {
        this.shieldStatus = status;
    }
    
}
