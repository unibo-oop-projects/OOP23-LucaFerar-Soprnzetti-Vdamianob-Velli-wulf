package javawulf.model.powerUp;

public abstract class PowerUpFactory {
    
    public PowerUp orderPowerUp(){
        PowerUp powerUp = createPowerUp();
        return powerUp;
    }

    public abstract PowerUp createPowerUp();
}
