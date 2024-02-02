package javawulf.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import java.awt.Rectangle;
import javawulf.model.BoundingBox;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;

public final class MapImpl implements Map {

    private final List<Biome> biomes = new ArrayList<>();
    private final java.util.Map<TilePosition, TileType> tiles = new HashMap<>();

    /**
     * Each element in BiomeQuadrant rapresent one of four biome of the map. Used for get their offset positions.
     */
    enum BiomeQuadrant {
        /**
         * First biome (upper-left)
         */
        FIRST(0, new TilePosition(0, 0)),
        /**
         * Second biome (upper-right)
         */
        SECOND(1, new TilePosition(Biome.SIZE + WIDTH_CENTRAL_BIOME, 0)),
        /**
         * Third biome (downer-right)
         */
        THIRD(2, new TilePosition(Biome.SIZE + WIDTH_CENTRAL_BIOME, Biome.SIZE + WIDTH_CENTRAL_BIOME)),
        /**
         * Fourth biome (downer-left)
         */
        FOURTH(3, new TilePosition(0, Biome.SIZE + WIDTH_CENTRAL_BIOME));

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

    /**
     * This is the constructor. Calling constructor, map will be built.
     * @param firstBiome
     * @param secondBiome
     * @param thirdBiome
     * @param fourthBiome
     */
    public MapImpl(Biome firstBiome, Biome secondBiome, Biome thirdBiome, Biome fourthBiome) {
        this.biomes.addAll(List.of(firstBiome, secondBiome, thirdBiome, fourthBiome));
        build();
    }

    @Override
    public Optional<TilePosition> getTilePosition(Coordinate position) {
        if (!this.isValidPosition(position)) {
            return Optional.empty();
        }
        return Optional.of(
                new TilePosition(position.getX() / TileType.TILE_DIMENSION, position.getY() / TileType.TILE_DIMENSION));
    }

    @Override
    public Optional<TileType> getTileType(Coordinate position) {
        var tilePos = this.getTilePosition(position);
        if (tilePos.isEmpty()) {
            return Optional.empty();
        }

        return (this.tiles.containsKey(tilePos.get()) ? Optional.of(tiles.get(tilePos.get()))
                : Optional.of(TileType.WALL));
    }

    @Override
    public Set<TileType> getTileTypes(BoundingBox boundBoxEntity) {
        Rectangle entityRect = boundBoxEntity.getCollisionArea();
        HashSet<TileType> intersectedTileTypes = new HashSet<>();
        if (!isValidPosition(new CoordinateImpl(entityRect.x,entityRect.y))) {
            return intersectedTileTypes;
        }

        for (int x = entityRect.x; x < entityRect.x + entityRect.width /* entityRect.x + (entityRect.width / TileType.TILE_DIMENSION) */; x++) {
            for (int y = entityRect.y; y < entityRect.y + entityRect.height /* entityRect.y + (entityRect.height / TileType.TILE_DIMENSION) */; y++) {
                intersectedTileTypes.add(this.getTileType(new CoordinateImpl(x, y)).get());
            }
        }
        return intersectedTileTypes;
    }

    private void build() {
        for (var biomeOffSet : BiomeQuadrant.values()) {
            for (var room : biomes.get(biomeOffSet.getPos()).getRooms()) {
                for (int y = room.getKey().getY(); y < room.getKey().getY() + room.getValue().getHeight(); y++) {
                    for (int x = room.getKey().getX(); x < room.getKey().getX() + room.getValue().getWidth(); x++) {
                        this.tiles.put(new TilePosition(x + biomeOffSet.getOffset().getX(),
                                y + biomeOffSet.getOffset().getY()), Room.defaultType);
                    }
                }
            }
        }

        for (var biomeOffSet : BiomeQuadrant.values()) {
            for (var corridor : biomes.get(biomeOffSet.getPos()).getCorridors()) {
                for (int y = corridor.getKey().getY(); y < corridor.getKey().getY() + corridor.getValue().getHeight(); y++) {
                    for (int x = corridor.getKey().getX(); x < corridor.getKey().getX() + corridor.getValue().getWidth(); x++) {
                        this.tiles.put(new TilePosition(x + biomeOffSet.getOffset().getX(),
                                y + biomeOffSet.getOffset().getY()), Corridor.defaultType);
                    }
                }

            }
        }

        this.buildCentralBiome();
    }

    private void buildCentralBiome() {
        for (int x = Biome.SIZE; x <= Biome.SIZE -1 + WIDTH_CENTRAL_BIOME; x++) {
            for (int y = 3; y < 5; y++) {
                this.tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int y = 15; y < 17; y++) {
                this.tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int y = Biome.SIZE+WIDTH_CENTRAL_BIOME+3; y < Biome.SIZE+WIDTH_CENTRAL_BIOME+5; y++) {
                this.tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int y = Biome.SIZE+WIDTH_CENTRAL_BIOME+15; y < Biome.SIZE+WIDTH_CENTRAL_BIOME+17; y++) {
                this.tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
        }

        for (int y = Biome.SIZE; y <= Biome.SIZE -1 + WIDTH_CENTRAL_BIOME; y++) {
            for (int x = 3; x < 5; x++) {
                this.tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int x = 15; x < 17; x++) {
                this.tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int x = Biome.SIZE+WIDTH_CENTRAL_BIOME+3; x < Biome.SIZE+WIDTH_CENTRAL_BIOME+5; x++) {
                this.tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
            for (int x = Biome.SIZE+WIDTH_CENTRAL_BIOME+15; x < Biome.SIZE+WIDTH_CENTRAL_BIOME+17; x++) {
                this.tiles.put(new TilePosition(x, y), TileType.CORRIDOR);
            }
        }

        for (int x = Biome.SIZE + 2; x <= Biome.SIZE + WIDTH_CENTRAL_BIOME - 1 - 2; x++) {
            for (int y = Biome.SIZE + 2; y <= Biome.SIZE + WIDTH_CENTRAL_BIOME - 1 - 2; y++) {
                this.tiles.put(new TilePosition(x, y), TileType.CENTRAL_ROOM);
            }
        }
        for (int x = Biome.SIZE + WIDTH_CENTRAL_BIOME/2 - 1; x < Biome.SIZE + WIDTH_CENTRAL_BIOME/2+1; x++) {
            for (int y = Biome.SIZE - 3; y < Biome.SIZE + 2; y++) {
                this.tiles.put(new TilePosition(x, y), TileType.CENTRAL_ROOM);
            }
            for (int y = Biome.SIZE + 8; y < Biome.SIZE + 13; y++) {
                this.tiles.put(new TilePosition(x, y), TileType.CENTRAL_ROOM);
            }
        }
        for (int y = Biome.SIZE + WIDTH_CENTRAL_BIOME/2 - 1; y < Biome.SIZE + WIDTH_CENTRAL_BIOME/2+1; y++) {
            for (int x = Biome.SIZE - 3; x < Biome.SIZE + 2; x++) {
                this.tiles.put(new TilePosition(x, y), TileType.CENTRAL_ROOM);
            }
            for (int x = Biome.SIZE + 8; x < Biome.SIZE + 13; x++) {
                this.tiles.put(new TilePosition(x, y), TileType.CENTRAL_ROOM);
            }
        }
    }

    private boolean isValidPosition(Coordinate pos) {
        return (pos.getX() < 0 || pos.getY() < 0 || (pos.getX() / TileType.TILE_DIMENSION) >= MAP_SIZE
                || (pos.getY() / TileType.TILE_DIMENSION) >= MAP_SIZE ? false : true);
    }

    @Override
    public HashMap<TilePosition, TileType> getTilesMap() {
        return new HashMap<>(this.tiles);
    }

}