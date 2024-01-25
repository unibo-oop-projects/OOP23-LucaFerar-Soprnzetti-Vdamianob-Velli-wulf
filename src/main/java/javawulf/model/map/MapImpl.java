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
    private final java.util.Map<TilePosition, TileType> tiles = new HashMap<>();

    enum BiomeOffset {
        FIRST(0, new TilePosition(0, 0)), // TODO: defines offsets!
        SECOND(1, new TilePosition(Biome.SIZE, 0)),
        THIRD(2, new TilePosition(2 * Biome.SIZE, 0)),
        FOURTH(3, new TilePosition(3 * Biome.SIZE, 0));

        private final int pos;
        private final TilePosition offset;

        BiomeOffset(int pos, TilePosition offset) {
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
        for (var biomeOffSet : BiomeOffset.values()) {
            for (var room : biomes.get(biomeOffSet.getPos()).getRooms()) {
                for(int y = room.getKey().getY(); y < room.getValue().getHeight(); y++) {
                    for(int x = room.getKey().getX(); x < room.getValue().getWidth(); x++) {
                        this.tiles.put(new TilePosition(x+biomeOffSet.getOffset().getX(), y+biomeOffSet.getOffset().getY()), Room.defaultType);
                    }
                }

            }
        }
    }

}
