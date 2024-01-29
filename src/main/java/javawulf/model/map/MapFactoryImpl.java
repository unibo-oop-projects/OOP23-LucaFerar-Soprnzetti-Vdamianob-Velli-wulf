package javawulf.model.map;

public class MapFactoryImpl implements MapFactory{

    private Biome firstBiome, secondBiome, thirdBiome, fourthBiome;
    private Map mapToGenerate;

    @Override
    public Map getDefaultMap1() {
        this.firstBiome = new BiomeImpl();
        // firstBiome.addRoom(new TilePosition(0, 0), new Room(10, 10));
        firstBiome.addRoom(new TilePosition(2, 2), new RoomFactoryImpl().getSquaredRoom());
        firstBiome.addRoom(new TilePosition(9, 3), new RoomFactoryImpl().getSquaredRoom());
        firstBiome.addRoom(new TilePosition(8, 11), new RoomFactoryImpl().getSquaredRoom());
        firstBiome.addCorridor(new TilePosition(7, 4), new Corridor(2, 2));
        firstBiome.addCorridor(new TilePosition(10, 8), new Corridor(2, 3));

        this.secondBiome = new BiomeImpl();
        secondBiome.addRoom(new TilePosition(2, 2), new RoomFactoryImpl().getSquaredRoom());
        secondBiome.addRoom(new TilePosition(13, 2), new RoomFactoryImpl().getSquaredRoom());
        secondBiome.addRoom(new TilePosition(12, 14), new RoomFactoryImpl().getSquaredRoom());
        secondBiome.addRoom(new TilePosition(1, 13), new RoomFactoryImpl().getSquaredRoom());
        secondBiome.addCorridor(new TilePosition(7, 4), new Corridor(6, 2));
        secondBiome.addCorridor(new TilePosition(10, 6), new Corridor(2, 6));
        secondBiome.addCorridor(new TilePosition(14, 10), new Corridor(2, 4));
        secondBiome.addCorridor(new TilePosition(14, 10), new Corridor(2, 4));
        secondBiome.addCorridor(new TilePosition(12, 10), new Corridor(2, 2));
        secondBiome.addCorridor(new TilePosition(3, 7), new Corridor(2, 6));

        this.thirdBiome = new BiomeImpl();
        thirdBiome.addRoom(new TilePosition(0, 0), new Room(10, 10));
        // thirdBiome.addRoom(new TilePosition(10, 6), new RoomFactoryImpl().getSquaredRoom());
        // thirdBiome.addRoom(new TilePosition(35, 35), new RoomFactoryImpl().getSquaredRoom());

        this.fourthBiome = new BiomeImpl();
        // fourthBiome.addRoom(new TilePosition(0, 0), new Room(10, 10));
        // fourthBiome.addRoom(new TilePosition(10, 6), new RoomFactoryImpl().getSquaredRoom());
        // fourthBiome.addRoom(new TilePosition(35, 35), new RoomFactoryImpl().getSquaredRoom());
        fourthBiome.addRoom(new TilePosition(3, 3), new Room(3, 3));
        fourthBiome.addRoom(new TilePosition(6, 6), new Room(3, 3));

        this.mapToGenerate = new MapImpl(firstBiome, secondBiome, thirdBiome, fourthBiome);

        return this.mapToGenerate;
    }

}
