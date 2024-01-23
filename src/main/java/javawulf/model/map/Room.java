package javawulf.model.map;

/**
 * Concept of Room
 * N rooms can be added to a biome. A Room have a default tileType with which the are filled.
 */
public interface Room {
    /**
     * Default tileType of the room, with which the are filled.
     */
    public static TileType defaultType = TileType.ROOM;

    /**
     * 
     * @return width (in tile) of the room
     */
    public int getWidth();

    /**
     * 
     * @return height (in tile) of the room
     */
    public int getHeight();
}
