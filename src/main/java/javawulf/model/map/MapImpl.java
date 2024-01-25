package javawulf.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MapImpl implements Map {
    /**
     * Default central cross-biome width (+)
     */
    private static int WIDTH_CENTRAL_BIOME = 12;
    private final int MAP_SIZE = Biome.SIZE * 2 + WIDTH_CENTRAL_BIOME;
    private final List<Biome> biomes = new ArrayList<>();
    private final java.util.Map<TilePosition, TileType> tiles = new HashMap<>();

    enum BiomeOffset {
        // TODO: verificare correttezza degli offsets!
        FIRST(0, new TilePosition(0, 0)),
        SECOND(1, new TilePosition(Biome.SIZE + WIDTH_CENTRAL_BIOME, 0)),
        THIRD(2, new TilePosition(Biome.SIZE + WIDTH_CENTRAL_BIOME, Biome.SIZE + WIDTH_CENTRAL_BIOME)),
        FOURTH(3, new TilePosition(0, Biome.SIZE + WIDTH_CENTRAL_BIOME));

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
    public Optional<TilePosition> getTilePosition(Position position) {
        if (!this.isValidPosition(position)) {
            return Optional.empty();
        }
        return Optional.of(
                new TilePosition(position.getX() / TileType.TILE_DIMENSION, position.getY() / TileType.TILE_DIMENSION));
    }

    @Override
    public Optional<TileType> getTileType(Position position) {
        var tilePos = this.getTilePosition(position);
        if (tilePos.isEmpty()) {
            return Optional.empty();
        }

        return (this.tiles.containsKey(tilePos.get()) ? Optional.of(tiles.get(tilePos.get()))
                : Optional.of(TileType.WALL));
    }

    private void build() {
        for (var biomeOffSet : BiomeOffset.values()) {
            for (var room : biomes.get(biomeOffSet.getPos()).getRooms()) {
                for (int y = room.getKey().getY(); y < room.getValue().getHeight(); y++) {
                    for (int x = room.getKey().getX(); x < room.getValue().getWidth(); x++) {
                        this.tiles.put(new TilePosition(x + biomeOffSet.getOffset().getX(),
                                y + biomeOffSet.getOffset().getY()), Room.defaultType);
                    }
                }

            }
        }

        for (var biomeOffSet : BiomeOffset.values()) {
            for (var corridor : biomes.get(biomeOffSet.getPos()).getCorridors()) {
                for (int y = corridor.getKey().getY(); y < corridor.getValue().getHeight(); y++) {
                    for (int x = corridor.getKey().getX(); x < corridor.getValue().getWidth(); x++) {
                        this.tiles.put(new TilePosition(x + biomeOffSet.getOffset().getX(),
                                y + biomeOffSet.getOffset().getY()), Corridor.defaultType);
                    }
                }

            }
        }
    }

    private boolean isValidPosition(Position pos) {
        return (pos.getX() < 0 || pos.getY() < 0 || (pos.getX() / TileType.TILE_DIMENSION) >= MAP_SIZE
                || (pos.getY() / TileType.TILE_DIMENSION) >= MAP_SIZE ? false : true);
    }
}