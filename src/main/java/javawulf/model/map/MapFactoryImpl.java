package javawulf.model.map;

public class MapFactoryImpl implements MapFactory{

    private Biome firstBiome, secondBiome, thirdBiome, fourthBiome;
    private Map mapToGenerate;

    @Override
    public Map defaultMap1() {
        this.firstBiome = new BiomeImpl();
        firstBiome.addRoom(new TilePosition(5, 5), new Room(5, 5));
        firstBiome.addRoom(new TilePosition(10, 6), new Room(5, 5));
        firstBiome.addRoom(new TilePosition(35, 35), new Room(5, 5));

        this.secondBiome = new BiomeImpl();
        secondBiome.addRoom(new TilePosition(5, 5), new Room(5, 5));
        secondBiome.addRoom(new TilePosition(10, 6), new Room(5, 5));
        secondBiome.addRoom(new TilePosition(35, 35), new Room(5, 5));

        this.thirdBiome = new BiomeImpl();
        thirdBiome.addRoom(new TilePosition(5, 5), new Room(5, 5));
        thirdBiome.addRoom(new TilePosition(10, 6), new Room(5, 5));
        thirdBiome.addRoom(new TilePosition(35, 35), new Room(5, 5));

        this.fourthBiome = new BiomeImpl();
        fourthBiome.addRoom(new TilePosition(5, 5), new Room(5, 5));
        fourthBiome.addRoom(new TilePosition(10, 6), new Room(5, 5));
        fourthBiome.addRoom(new TilePosition(35, 35), new Room(5, 5));

        this.mapToGenerate = new MapImpl(firstBiome, secondBiome, thirdBiome, fourthBiome);

        return this.mapToGenerate;
    }

}
