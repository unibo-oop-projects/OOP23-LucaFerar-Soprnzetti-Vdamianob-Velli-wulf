package javawulf.model.player;

public class PlayerHealthImpl implements PlayerHealth {

    private int health;
    private int maxHealth;
    private ShieldStatus shieldStatus;

    /**
     * Creates the player's health statistics for the current game.
     * The starting health indicates also the current maximum amount
     * of health the player has at the beginning of the game
     * 
     * @param startingHealth The amount of health the player starts the game with 
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
        if (health<0){
            this.damage(health);
        } else {
            this.health = Math.min((this.health + health), this.maxHealth);
        }
    }

    private void damage(int health){
        if(this.getShieldStatus().equals(ShieldStatus.NONE)){
            this.health = this.health + health;
            System.out.println("Health remaining : " + this.getHealth());
        } else {
            if (this.getShieldStatus().equals(ShieldStatus.FULL)){
                this.setShieldStatus(ShieldStatus.HALF);
                System.out.println("Shield hits remaining :" + this.getShieldStatus().getStrength());
            } else {
                this.setShieldStatus(ShieldStatus.NONE);
                System.out.println("Shield broke!");
            }
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

    public boolean equals(PlayerHealth hp){
        return this.getHealth() == hp.getHealth() && this.getMaxHealth() == hp.getMaxHealth() &&
            this.getShieldStatus() == hp.getShieldStatus();
    }
    
}
