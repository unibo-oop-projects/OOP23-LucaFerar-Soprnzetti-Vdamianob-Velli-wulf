package javawulf.model;

import javawulf.model.PlayerImpl.SwordType;

public interface Player {
    
    public void attack();

    public SwordType getSwordType();

    public void move() throws IllegalStateException;

    public boolean isHit();

    public void changeSwordType();

    public boolean isAmuletPieceInCoordinate();

    public int getHealth();

    public void usePowerUp(PowerUp p);

    public boolean isDefeated();

    public int getScore();

    public void increaseScore(int points);
}
