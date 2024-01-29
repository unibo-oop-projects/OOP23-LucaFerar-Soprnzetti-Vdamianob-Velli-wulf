package javawulf.model.player;

import java.awt.Color;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javawulf.model.BoundingBox;
import javawulf.model.Direction;
import javawulf.model.Entity;
import javawulf.model.GameElement;
import javawulf.model.item.AmuletPiece;
import javawulf.model.powerUp.PowerUp;

/**
 * Player represents the playable character and its statitstics
 */
public interface Player extends GameElement, Entity {

    public enum PlayerColor{
        RED(Optional.of(Color.RED)),
        BLUE(Optional.of(Color.BLUE)),
        YELLOW(Optional.of(Color.YELLOW)),
        GREEN(Optional.of(Color.GREEN)),
        NONE(Optional.empty());

        private final Optional<Color> color;

        private PlayerColor(Optional<Color> color){
            this.color = color;
        }

        public Color getColor() throws NoSuchElementException{
            return this.color.orElseThrow();
        }
    }

    /**
     * Activate the sword in order to attack
     */
    public void attack();

    /**
     * Move in the specified direction
     * 
     * @param direction The direction the player character must move towards
     * @throws IllegalStateException If the character can't continue in that direction
     * (due to a wall) 
     */
    public void move(Direction direction) throws IllegalStateException;

    /**
     * Check if player is getting hit by an enemy. If it is the case, then the player
     * character will be subject to damage
     * 
     * @param box BoundingBox that must be checked
     */
    public boolean isHit(BoundingBox box);

    public void collectAmuletPiece(AmuletPiece piece);

    /**
     * @return The current health of the player character, including also the maximum
     * amount of health currently obtainable and his shield
     */
    public PlayerHealth getPlayerHealth();

    public void usePowerUp(PowerUp p);

    public boolean isDefeated();

    /**
     * @return The current point total and point multiplier
     */
    public Score getScore();

    /**
     * @return The player's sword
     */
    public Sword getSword();

    public PlayerColor getColor();

    public void setColor(PlayerColor color);

    public List<AmuletPiece> getPieces();
    
}
