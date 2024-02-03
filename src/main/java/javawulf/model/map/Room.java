package javawulf.model.map;

/**
 * Room implementation of area.
 */
public final class Room extends AbstractSpace {

    /**
     * Default rooms' tile-type
     */
    public static TileType DEFAULT_TYPE = TileType.ROOM;

    /**
     * 
     * @param width of the room
     * @param height of the room
     */
    public Room(int width, int height) {
        super(width, height);
    }

}
