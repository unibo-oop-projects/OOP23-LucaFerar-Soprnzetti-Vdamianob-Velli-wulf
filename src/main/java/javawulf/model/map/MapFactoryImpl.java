package javawulf.model.map;

public class MapFactoryImpl implements MapFactory{

    private Biome firstBiome, secondBiome, thirdBiome, fourthBiome;
    private Map mapToGenerate;

    @Override
    public Map defaultMap1() {
        this.firstBiome = new BiomeImpl();
        // firstBiome.addRoom(new TilePosition(0, 0), new Room(10, 10));
        firstBiome.addRoom(new TilePosition(2, 2), new Room(5, 5));
        firstBiome.addRoom(new TilePosition(9, 3), new Room(5, 5));
        firstBiome.addRoom(new TilePosition(8, 11), new Room(5, 5));
        firstBiome.addCorridor(new TilePosition(7, 4), new Corridor(2, 2));
        firstBiome.addCorridor(new TilePosition(10, 8), new Corridor(2, 3));

        this.secondBiome = new BiomeImpl();
        secondBiome.addRoom(new TilePosition(0, 0), new Room(10, 10));
        // secondBiome.addRoom(new TilePosition(10, 6), new Room(5, 5));
        // secondBiome.addRoom(new TilePosition(35, 35), new Room(5, 5));

        this.thirdBiome = new BiomeImpl();
        thirdBiome.addRoom(new TilePosition(0, 0), new Room(10, 10));
        // thirdBiome.addRoom(new TilePosition(10, 6), new Room(5, 5));
        // thirdBiome.addRoom(new TilePosition(35, 35), new Room(5, 5));

        this.fourthBiome = new BiomeImpl();
        // fourthBiome.addRoom(new TilePosition(0, 0), new Room(10, 10));
        // fourthBiome.addRoom(new TilePosition(10, 6), new Room(5, 5));
        // fourthBiome.addRoom(new TilePosition(35, 35), new Room(5, 5));
        fourthBiome.addRoom(new TilePosition(3, 3), new Room(3, 3));
        fourthBiome.addRoom(new TilePosition(6, 6), new Room(3, 3));

        this.mapToGenerate = new MapImpl(firstBiome, secondBiome, thirdBiome, fourthBiome);

        return this.mapToGenerate;
    }

}
