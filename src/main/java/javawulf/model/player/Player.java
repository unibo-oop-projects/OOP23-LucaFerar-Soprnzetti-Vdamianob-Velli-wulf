package javawulf.model.player;

import javawulf.model.BoundingBox;
import javawulf.model.Direction;
import javawulf.model.GameElement;
import javawulf.model.powerUp.PowerUp;

public interface Player extends GameElement {

    public void attack();

    public void move(Direction direction) throws IllegalStateException;

    public boolean isHit(BoundingBox b);

    public boolean isAmuletPieceInCoordinate();

    public PlayerHealth getPlayerHealth();

    public void usePowerUp(PowerUp p);

    public boolean isDefeated();

    public Score getScore();

    public Sword getSword();
    
    public void setScore(Score score);

    public void setSword(Sword sword);

    public void setPlayerHealth(PlayerHealth health);
    
}
