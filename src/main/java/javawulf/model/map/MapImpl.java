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
    private final java.util.Map<TilePosition, TileType> tiles;

    /**
     * This is the constructor. Calling constructor, map will be built.
     * 
     * @param firstBiome
     * @param secondBiome
     * @param thirdBiome
     * @param fourthBiome
     */
    public MapImpl(Biome firstBiome, Biome secondBiome, Biome thirdBiome, Biome fourthBiome) {
        this.biomes.addAll(List.of(firstBiome, secondBiome, thirdBiome, fourthBiome));
        this.tiles = MapTilesBuilder.buildTiles(this.biomes);
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
        if (!isValidPosition(new CoordinateImpl(entityRect.x, entityRect.y))) {
            return intersectedTileTypes;
        }

        for (int x = entityRect.x; x < entityRect.x + entityRect.width /*
                                                                        * entityRect.x + (entityRect.width /
                                                                        * TileType.TILE_DIMENSION)
                                                                        */; x++) {
            for (int y = entityRect.y; y < entityRect.y + entityRect.height /*
                                                                             * entityRect.y + (entityRect.height /
                                                                             * TileType.TILE_DIMENSION)
                                                                             */; y++) {
                intersectedTileTypes.add(this.getTileType(new CoordinateImpl(x, y)).get());
            }
        }
        return intersectedTileTypes;
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