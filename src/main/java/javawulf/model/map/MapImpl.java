package javawulf.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javawulf.model.PositionOnMap;

public class MapImpl implements Map {
    /**
     * Default central cross-biome width
     */
    private static int WIDTH_CENTRAL_BIOME = 12;
    private final int MAP_SIZE = Biome.SIZE * 2 + WIDTH_CENTRAL_BIOME;
    private final List<Biome> biomes = new ArrayList<>();
    private final java.util.Map<TilePosition, Tile> tiles = new HashMap<>();

    public MapImpl(Biome firstBiome, Biome secondBiome, Biome thirdBiome, Biome fourthBiome) {
        this.biomes.addAll(List.of(firstBiome, secondBiome, thirdBiome, fourthBiome));
        build();
    }

    @Override
    public TilePosition getTilePosition(PositionOnMap position) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTilePosition'");
    }

    @Override
    public TileType getTileType(PositionOnMap position) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTileType'");
    }

    private void build() {
        
    }

}
