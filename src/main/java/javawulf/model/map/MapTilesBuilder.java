package javawulf.model.map;

import java.util.HashMap;

/**
 * A Utility that creates a HashMap<TilePosition, TileType> from four biomes.
 * The HashMap associates for each position a tile-type, thus composing the game
 * map.
 * The class that extends from this interface, will be exclusively used by
 * MapImpl for the sole purpose of making, internally, the map.
 */
public final class MapTilesBuilder {

    public HashMap<TilePosition, TileType> buildTiles(Biome firstBiome, Biome secondBiome, Biome thirdBiome,
            Biome fourthBiome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buildTiles'");
    }

}
