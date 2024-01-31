package javawulf.model.map;

/**
 * Generic concept of Space: a rectangular area in the map where player and enemies can walk.
 * Spaces can be rooms and corridors.
 */
public interface Space {

    /**
     * 
     * @return width (in tile) of the space
     */
    int getWidth();

    /**
     * 
     * @return height (in tile) of the space
     */
    int getHeight();
}
