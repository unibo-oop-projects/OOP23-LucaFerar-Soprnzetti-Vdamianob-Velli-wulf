package javawulf.model.map;

import javawulf.model.GameElement;
import java.util.List;

/**
 * Generic concept of Space: a rectangular area in the map where player and enemies can walk.
 * Spaces can be rooms and corridors.
 */
public interface Space {

    /**
     * 
     * @return width (in tile) of the space.
     */
    int getWidth();

    /**
     * 
     * @return height (in tile) of the space.
     */
    int getHeight();

    /**
     * 
     * @param element to add in the Space.
     */
    void addGameElement(GameElement element);

    /**
     * 
     * @return a list of gameElements referred to this space.
     */
    List<GameElement> getElements();
}
