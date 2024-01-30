package javawulf.model.map;

/**
 * Corridor implementation of area
 */
public class Corridor extends AbstractSpace {

    /**
     * Default corridors' tile-type
     */
    public static TileType defaultType = TileType.CORRIDOR;

    /**
     * 
     * @param width of the corridor
     * @param height of the corridor
     */
    public Corridor(int width, int height) {
        super(width, height);
    }

}
