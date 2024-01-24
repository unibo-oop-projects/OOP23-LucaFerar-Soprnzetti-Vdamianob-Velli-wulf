package javawulf.model;

public class PlayerHealthImpl implements PlayerHealth {

    private int health;
    private int maxHealth;
    private ShieldStatus shieldStatus;

    /**
     * Creates the player's health statistics for the current game.
     * The starting health indicates also the current maximum amount
     * of health the player has at the beginning of the game
     * 
     * @param startingHealth the amount of health the player begins the game with 
     */
    public PlayerHealthImpl(int startingHealth){
        this.health = startingHealth;
        this.maxHealth = startingHealth;
        this.shieldStatus = ShieldStatus.NONE;
    }

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
        if (this.health + health < this.maxHealth) {
            this.health += health;
        } else {
            this.health = this.maxHealth;
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
