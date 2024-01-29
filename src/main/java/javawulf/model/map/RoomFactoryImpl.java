package javawulf.model.map;

public class RoomFactoryImpl implements RoomFactory {

    @Override
    public Room getSquaredRoom() {
        return new Room(5, 5);
    }

}
