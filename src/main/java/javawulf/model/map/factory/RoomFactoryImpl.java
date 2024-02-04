package javawulf.model.map.factory;

import javawulf.model.map.Room;

/**
 * Implementation of RoomFactory, which is used to create default rooms. Useful
 * in BiomeFactories or standalone in tests.
 */
public final class RoomFactoryImpl implements RoomFactory {

    private final int mediumDefDim = 5;

    @Override
    public Room getSquaredRoom() {
        return new Room(this.mediumDefDim, this.mediumDefDim);
    }

}
