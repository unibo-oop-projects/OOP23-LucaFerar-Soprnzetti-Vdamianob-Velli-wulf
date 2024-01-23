package javawulf.model;

public interface Player extends GameElement {

    public void attack();

    public void move() throws IllegalStateException;

    public boolean isHit(BoundingBox b);

    public boolean isAmuletPieceInCoordinate();

    public PlayerHealth getHealth();

    public void usePowerUp(PowerUp p);

    public boolean isDefeated();

    public Score getScore();

    public Sword getSword();
    
    public void setScore(Score score);

    public void setSword(Sword sword);

    public void setHealth(PlayerHealth health);
    
}
