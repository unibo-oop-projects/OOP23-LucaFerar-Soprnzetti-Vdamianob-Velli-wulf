package javawulf.model.map;

public class Room extends AbstractSpace {

    public static TileType defaultType = TileType.ROOM;

    public Room(int width, int height) {
        super(width, height);
    }

}
