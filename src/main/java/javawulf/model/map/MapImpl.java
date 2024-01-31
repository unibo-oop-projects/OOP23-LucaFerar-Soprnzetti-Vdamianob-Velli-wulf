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
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;

/**
 * Implementation of Map interface.
 * All important details are written in the interface: please, visit {@link Map} javadoc
 * @see Map
 */
public final class MapImpl implements Map {

    private final List<Biome> biomes = new ArrayList<>();
    private final java.util.Map<TilePosition, TileType> tiles;
    private final Player player;

    /**
     * This is the constructor. Calling constructor, map will be built.
     * 
     * @param firstBiome
     * @param secondBiome
     * @param thirdBiome
     * @param fourthBiome
     */
    public MapImpl(final Biome firstBiome, final Biome secondBiome, final Biome thirdBiome, final Biome fourthBiome) {
        this.biomes.addAll(List.of(firstBiome, secondBiome, thirdBiome, fourthBiome));
        this.tiles = MapTilesBuilder.buildTiles(this.biomes);
        // TODO: to edit
        final int defaultPlayerValue = 1;
        this.player = new PlayerImpl(TileType.TILE_DIMENSION, TileType.TILE_DIMENSION, defaultPlayerValue, defaultPlayerValue);
    }

    @Override
    public Optional<TilePosition> getTilePosition(final Coordinate position) {
        if (!this.isValidPosition(position)) {
            return Optional.empty();
        }
        return Optional.of(
                new TilePosition(position.getX() / TileType.TILE_DIMENSION, position.getY() / TileType.TILE_DIMENSION));
    }

    @Override
    public Optional<TileType> getTileType(final Coordinate position) {
        var tilePos = this.getTilePosition(position);
        if (tilePos.isEmpty()) {
            return Optional.empty();
        }

        return (this.tiles.containsKey(tilePos.get()) ? Optional.of(tiles.get(tilePos.get()))
                : Optional.of(TileType.WALL));
    }

    @Override
    public Set<TileType> getTileTypes(final BoundingBox boundBoxEntity) {
        Rectangle entityRect = boundBoxEntity.getCollisionArea();
        HashSet<TileType> intersectedTileTypes = new HashSet<>();
        if (!isValidPosition(new CoordinateImpl(entityRect.x, entityRect.y))) {
            return intersectedTileTypes;
        }

        for (int x = entityRect.x; x < entityRect.x + entityRect.width; x++) {
            for (int y = entityRect.y; y < entityRect.y + entityRect.height; y++) {
                intersectedTileTypes.add(this.getTileType(new CoordinateImpl(x, y)).get());
            }
        }
        return intersectedTileTypes;
    }

    private boolean isValidPosition(final Coordinate pos) {
        return (pos.getX() < 0 || pos.getY() < 0 || (pos.getX() / TileType.TILE_DIMENSION) >= MAP_SIZE
                || (pos.getY() / TileType.TILE_DIMENSION) >= MAP_SIZE ? false : true);
    }

    @Override
    public HashMap<TilePosition, TileType> getTilesMap() {
        return new HashMap<>(this.tiles);
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

}
