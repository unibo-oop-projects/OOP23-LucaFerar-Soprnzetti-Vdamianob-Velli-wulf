package javawulf.model;

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
