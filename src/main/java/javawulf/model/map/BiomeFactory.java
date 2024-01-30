package javawulf.model.map;

public interface BiomeFactory {
    /**
     * 
     * @return a simple Biome with three 5x5 rooms.
     */
    Biome getBiomeA();
    /**
     * 
     * @return a quite simple biome with four 5x5 rooms.
     */
    Biome getBiomeB();
    /**
     * 
     * @return a quite simple biome with four 5x5 rooms.
     */
    Biome getBiomeC();
    /**
     * 
     * @return a particular biome with four 5x5 rooms, but one of it is linked only with other biomes.
     */
    Biome getBiomeD();
}
