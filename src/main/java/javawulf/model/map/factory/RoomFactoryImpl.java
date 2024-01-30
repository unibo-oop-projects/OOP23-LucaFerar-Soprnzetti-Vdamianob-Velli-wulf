package javawulf.model.map.factory;

import javawulf.model.map.Room;

public class RoomFactoryImpl implements RoomFactory {

    @Override
    public Room getSquaredRoom() {
        return new Room(5, 5);
    }

}
