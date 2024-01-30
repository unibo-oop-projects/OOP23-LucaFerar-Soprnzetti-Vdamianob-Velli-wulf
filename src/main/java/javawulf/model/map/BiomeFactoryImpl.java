package javawulf.model.map;

public class BiomeFactoryImpl implements BiomeFactory {

    @Override
    public Biome getBiomeA() {
        return new BiomeImpl()
                .addRoom(new TilePosition(2, 2), new RoomFactoryImpl().getSquaredRoom())
                .addRoom(new TilePosition(9, 3), new RoomFactoryImpl().getSquaredRoom())
                .addRoom(new TilePosition(8, 11), new RoomFactoryImpl().getSquaredRoom())
                .addCorridor(new TilePosition(7, 4), new Corridor(2, 2))
                .addCorridor(new TilePosition(10, 8), new Corridor(2, 3));
    }

    @Override
    public Biome getBiomeB() {
        return new BiomeImpl()
                .addRoom(new TilePosition(2, 2), new RoomFactoryImpl().getSquaredRoom())
                .addRoom(new TilePosition(13, 2), new RoomFactoryImpl().getSquaredRoom())
                .addRoom(new TilePosition(12, 14), new RoomFactoryImpl().getSquaredRoom())
                .addRoom(new TilePosition(1, 13), new RoomFactoryImpl().getSquaredRoom())
                .addCorridor(new TilePosition(7, 4), new Corridor(6, 2))
                .addCorridor(new TilePosition(10, 6), new Corridor(2, 6))
                .addCorridor(new TilePosition(14, 10), new Corridor(2, 4))
                .addCorridor(new TilePosition(14, 10), new Corridor(2, 4))
                .addCorridor(new TilePosition(12, 10), new Corridor(2, 2))
                .addCorridor(new TilePosition(3, 7), new Corridor(2, 6));
    }

    @Override
    public Biome getBiomeC() {
        return new BiomeImpl()
                .addRoom(new TilePosition(2, 2), new RoomFactoryImpl().getSquaredRoom())
                .addRoom(new TilePosition(13, 2), new RoomFactoryImpl().getSquaredRoom())
                .addRoom(new TilePosition(12, 14), new RoomFactoryImpl().getSquaredRoom())
                .addRoom(new TilePosition(1, 13), new RoomFactoryImpl().getSquaredRoom())
                .addCorridor(new TilePosition(6, 15), new Corridor(2, 2))
                .addCorridor(new TilePosition(8, 10), new Corridor(2, 7))
                .addCorridor(new TilePosition(14, 7), new Corridor(2, 7))
                .addCorridor(new TilePosition(14, 10), new Corridor(2, 4))
                .addCorridor(new TilePosition(10, 10), new Corridor(4, 2));
    }

    @Override
    public Biome getBiomeD() {
        return new BiomeImpl()
                .addRoom(new TilePosition(2, 4), new RoomFactoryImpl().getSquaredRoom())
                .addRoom(new TilePosition(13, 2), new RoomFactoryImpl().getSquaredRoom())
                .addRoom(new TilePosition(12, 14), new RoomFactoryImpl().getSquaredRoom())
                .addRoom(new TilePosition(1, 13), new RoomFactoryImpl().getSquaredRoom())
                .addCorridor(new TilePosition(7, 5), new Corridor(5, 2))
                .addCorridor(new TilePosition(10, 3), new Corridor(3, 2))
                .addCorridor(new TilePosition(10, 6), new Corridor(2, 6))
                .addCorridor(new TilePosition(14, 10), new Corridor(2, 4))
                .addCorridor(new TilePosition(14, 10), new Corridor(2, 4))
                .addCorridor(new TilePosition(12, 10), new Corridor(2, 2))
                .addCorridor(new TilePosition(6, 15), new Corridor(6, 2));
    }

    @Override
    public Biome getTestBiome() {
        return new BiomeImpl()
            .addRoom(new TilePosition(0, 0), new Room(Biome.SIZE, Biome.SIZE/3));
    }

    @Override
    public Biome getRoomBiome() {
        return new BiomeImpl()
            .addRoom(new TilePosition(0, 0), new Room(Biome.SIZE, Biome.SIZE));
    }

}
