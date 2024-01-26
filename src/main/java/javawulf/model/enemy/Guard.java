package javawulf.model.enemy;

import javawulf.model.Coordinate;
import javawulf.model.player.Player;

public class Guard extends EnemyImpl {

    private final static int KILLVALUE = 2;
    private boolean isAlive;
    private boolean isStunned;
    private long stunTime;

    public Guard(Coordinate position, Integer speed, int points) {
        super(position, speed, points);
        this.isAlive = true;
        this.isStunned = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isKillable(Player p) {
        return p.getSword().getSwordStrength() == KILLVALUE;
    }

    public void checkRoom() {
        // TODO implement here
    }

    public void stun(Integer time) {
        this.isStunned = true;
        this.stunTime = System.currentTimeMillis() + time * 1000;
    }

    @Override
    public void move(Player p) {
        if (this.isStunned) {
            if (System.currentTimeMillis() >= this.stunTime) {
                this.isStunned = false;
            }
        } else {
            
            int diffX = p.getPosition().getX() - this.getPosition().getX();
            int diffY = p.getPosition().getY() - this.getPosition().getY(); 

            if (Math.abs(diffX) > Math.abs(diffY)) {
                this.getPosition().setPosition(this.getPosition().getX() + (int)Math.signum(dx) * this.getSpeed(), 
                                           this.getPosition().getY());
            } else {
                this.getPosition().setPosition(this.getPosition().getX(), 
                                           this.getPosition().getY() + (int)Math.signum(dy) * this.getSpeed());
            }
        }
    }

    @Override
    public void takeHit(Player p) {
        if (this.isKillable(p)) {
            this.setAlive(false);
            p.getScore().addPoints(this.getPoints());
        } else {
            this.stun(4);
        }
    }
}
