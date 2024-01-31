package javawulf.model;

/**
 * GameElement represents an element of the game.
 */
public interface GameElement {

    /**
     * @return The BoundingBox of the element
     */
    BoundingBox getBounds();

    /**
     * @return The current Coordinate of the element
     */
    Coordinate getPosition();

    /**
     * @param b The BoundingBox the entity must now have
     */
    void setBounds(BoundingBox b);

    /**
     * @param p The Coordinate the entity must now have
     */
    void setPosition(Coordinate p);
}
