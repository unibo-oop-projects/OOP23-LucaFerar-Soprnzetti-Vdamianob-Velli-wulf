package javawulf.model.map;

import java.util.HashMap;
import java.util.List;

/**
 * An Utility class that creates a HashMap<TilePosition, TileType> from four
 * biomes.
 * The HashMap associates for each position a tile-type, thus composing the game
 * map.
 * The class that extends from this interface, will be exclusively used by
 * MapImpl for the sole purpose of making, internally, the map.
 */
public final class MapTilesBuilder {

    /**
     * Each element in BiomeQuadrant represent one of four biome of the map. Used
     * for get their offset positions.
     */
    enum BiomeQuadrant {
        /**
         * First biome (upper-left)
         */
        FIRST(0, new TilePosition(0, 0)),
        /**
         * Second biome (upper-right)
         */
        SECOND(1, new TilePosition(Biome.SIZE + Map.WIDTH_CENTRAL_BIOME, 0)),
        /**
         * Third biome (downer-right)
         */
        THIRD(2, new TilePosition(Biome.SIZE + Map.WIDTH_CENTRAL_BIOME, Biome.SIZE + Map.WIDTH_CENTRAL_BIOME)),
        /**
         * Fourth biome (downer-left)
         */
        FOURTH(3, new TilePosition(0, Biome.SIZE + Map.WIDTH_CENTRAL_BIOME));

        private final int pos;
        private final TilePosition offset;

        BiomeQuadrant(int pos, TilePosition offset) {
            this.pos = pos;
            this.offset = offset;
        }

        public TilePosition getOffset() {
            return this.offset;
        }

        public int getPos() {
            return this.pos;
        }
    }

    public static HashMap<TilePosition, TileType> buildTiles(final List<Biome> biomes) {
        return build(biomes);
    }

    private static HashMap<TilePosition, TileType> build(final List<Biome> biomes) {
        final HashMap<TilePosition, TileType> tiles = new HashMap<>();
        for (var biomeOffSet : BiomeQuadrant.values()) {
            for (var room : biomes.get(biomeOffSet.getPos()).getRooms()) {
                for (int y = room.getKey().getY(); y < room.getKey().getY() + room.getValue().getHeight(); y++) {
                    for (int x = room.getKey().getX(); x < room.getKey().getX() + room.getValue().getWidth(); x++) {
                        tiles.put(new TilePosition(x + biomeOffSet.getOffset().getX(),
                                y + biomeOffSet.getOffset().getY()), Room.defaultType);
                    }
                }
            }
        }

        for (var biomeOffSet : BiomeQuadrant.values()) {
            for (var corridor : biomes.get(biomeOffSet.getPos()).getCorridors()) {
                for (int y = corridor.getKey().getY(); y < corridor.getKey().getY()
                        + corridor.getValue().getHeight(); y++) {
                    for (int x = corridor.getKey().getX(); x < corridor.getKey().getX()
                            + corridor.getValue().getWidth(); x++) {
                        tiles.put(new TilePosition(x + biomeOffSet.getOffset().getX(),
                                y + biomeOffSet.getOffset().getY()), Corridor.defaultType);
                    }
                }

            }
        }

        buildCentralBiome(tiles);

        return tiles;
    }

    private static void buildCentralBiome(HashMap<TilePosition, TileType> tiles) {
        for (int x = Biome.SIZE; x <= Biome.SIZE - 1 + Map.WIDTH_CENTRAL_BIOME; x++) {
            for (int y = 3; y < 5; y++) {
                tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int y = 15; y < 17; y++) {
                tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int y = Biome.SIZE + Map.WIDTH_CENTRAL_BIOME + 3; y < Biome.SIZE + Map.WIDTH_CENTRAL_BIOME + 5; y++) {
                tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int y = Biome.SIZE + Map.WIDTH_CENTRAL_BIOME + 15; y < Biome.SIZE + Map.WIDTH_CENTRAL_BIOME + 17; y++) {
                tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
        }

        for (int y = Biome.SIZE; y <= Biome.SIZE - 1 + Map.WIDTH_CENTRAL_BIOME; y++) {
            for (int x = 3; x < 5; x++) {
                tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int x = 15; x < 17; x++) {
                tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int x = Biome.SIZE + Map.WIDTH_CENTRAL_BIOME + 3; x < Biome.SIZE + Map.WIDTH_CENTRAL_BIOME + 5; x++) {
                tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int x = Biome.SIZE + Map.WIDTH_CENTRAL_BIOME + 15; x < Biome.SIZE + Map.WIDTH_CENTRAL_BIOME
                    + 17; x++) {
                tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
        }

        for (int x = Biome.SIZE + 2; x <= Biome.SIZE + Map.WIDTH_CENTRAL_BIOME - 1 - 2; x++) {
            for (int y = Biome.SIZE + 2; y <= Biome.SIZE + Map.WIDTH_CENTRAL_BIOME - 1 - 2; y++) {
                tiles.put(new TilePosition(x, y), TileType.CENTRAL_ROOM);
            }
        }
        for (int x = Biome.SIZE + Map.WIDTH_CENTRAL_BIOME / 2 - 1; x < Biome.SIZE + Map.WIDTH_CENTRAL_BIOME / 2 + 1; x++) {
            for (int y = Biome.SIZE - 3; y < Biome.SIZE + 2; y++) {
                tiles.put(new TilePosition(x, y), TileType.CENTRAL_ROOM);
            }
            for (int y = Biome.SIZE + 8; y < Biome.SIZE + 13; y++) {
                tiles.put(new TilePosition(x, y), TileType.CENTRAL_ROOM);
            }
        }
        for (int y = Biome.SIZE + Map.WIDTH_CENTRAL_BIOME / 2 - 1; y < Biome.SIZE + Map.WIDTH_CENTRAL_BIOME / 2 + 1; y++) {
            for (int x = Biome.SIZE - 3; x < Biome.SIZE + 2; x++) {
                tiles.put(new TilePosition(x, y), TileType.CENTRAL_ROOM);
            }
            for (int x = Biome.SIZE + 8; x < Biome.SIZE + 13; x++) {
                tiles.put(new TilePosition(x, y), TileType.CENTRAL_ROOM);
            }
        }
    }

}
