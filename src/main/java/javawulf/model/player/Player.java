package javawulf.model.player;

import java.awt.Color;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javawulf.model.Direction;
import javawulf.model.Entity;
import javawulf.model.item.AmuletPiece;
import javawulf.model.powerUp.PowerUpHandler;

/**
 * Player represents the playable character and its statitstics
 */
public interface Player extends Entity {

    public enum PlayerColor {
        RED(Optional.of(Color.RED)),
        BLUE(Optional.of(Color.BLUE)),
        YELLOW(Optional.of(Color.YELLOW)),
        GREEN(Optional.of(Color.GREEN)),
        NONE(Optional.empty());

        private final Optional<Color> color;

        PlayerColor(Optional<Color> color) {
            this.color = color;
        }

        public Color getColor() throws NoSuchElementException {
            return this.color.orElseThrow();
        }
    }

    /**
     * Activate the sword in order to attack
     */
    void attack();

    /**
     * Move in the specified direction
     * 
     * @param direction The direction the player character must move towards
     * @throws IllegalStateException If the character can't continue in that direction
     * (due to a wall) 
     */
    void move(Direction direction) throws IllegalStateException;

    void collectAmuletPiece(AmuletPiece piece);

    /**
     * @return The current health of the player character, including also the maximum
     * amount of health currently obtainable and his shield
     */
    PlayerHealth getPlayerHealth();

    PowerUpHandler getPowerUpHandler();

    /**
     * @return The current point total and point multiplier
     */
    Score getScore();

    /**
     * @return The player's sword
     */
    Sword getSword();

    PlayerColor getColor();

    void setColor(PlayerColor color);

    List<AmuletPiece> getPieces();

}
