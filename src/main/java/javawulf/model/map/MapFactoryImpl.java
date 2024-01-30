package javawulf.model.map;

public class MapFactoryImpl implements MapFactory {

    private Biome firstBiome, secondBiome, thirdBiome, fourthBiome;

    @Override
    public Map getDefaultMap1() {
        this.firstBiome = new BiomeFactoryImpl().getBiomeA();

        this.secondBiome = new BiomeFactoryImpl().getBiomeB();

        this.thirdBiome = new BiomeFactoryImpl().getBiomeC();

        this.fourthBiome = new BiomeFactoryImpl().getBiomeD();

        return new MapImpl(this.firstBiome, this.secondBiome, this.thirdBiome, this.fourthBiome);
    }

}
