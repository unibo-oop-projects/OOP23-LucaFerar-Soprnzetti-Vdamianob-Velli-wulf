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
import javawulf.model.GameElement;
import javawulf.model.player.Player;

/**
 * Implementation of Map interface.
 * All important details are written in the interface: please, visit {@link Map} javadoc
 * @see Map
 */
public final class MapImpl implements Map {

    private ArrayList<Biome> biomes = new ArrayList<>();
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
    public MapImpl(final Player player, final Biome firstBiome, final Biome secondBiome, final Biome thirdBiome, final Biome fourthBiome) {
        this.biomes.addAll(List.of(firstBiome, secondBiome, thirdBiome, fourthBiome));
        this.tiles = MapTilesBuilder.buildTiles(this.biomes);
        this.player = player;
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

    private Set<TilePosition> getTiles(final BoundingBox boundBoxEntity) {
        Rectangle entityRect = boundBoxEntity.getCollisionArea();
        HashSet<TilePosition> intersectedTiles = new HashSet<>();
        if (!isValidPosition(new CoordinateImpl(entityRect.x, entityRect.y))) {
            return intersectedTiles;
        }

        for (int x = entityRect.x; x < entityRect.x + entityRect.width; x++) {
            for (int y = entityRect.y; y < entityRect.y + entityRect.height; y++) {
                intersectedTiles.add(this.getTilePosition(new CoordinateImpl(x, y)).get());
            }
        }
        return intersectedTiles;
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

    public ArrayList<Biome> getBiomes() {
        return this.biomes;
    }

    @Override
    public Optional<Space> getPlayerRoom() {
        System.out.println("Player tiles: " + this.getTiles(this.player.getBounds()));
        for (var playerTile : this.getTiles(this.player.getBounds())) {
            // System.out.println("PlayerTile: " + playerTile);
            Optional<BiomeQuadrant> quadrant = getBiomeQuadrant(playerTile);
            if (quadrant.isPresent()) {
                // System.out.println("Biome: " + quadrant.get() + " - " + quadrant.get().getOffset());
                System.out.println("Relative to Biome Tile-position: " + new TilePosition(playerTile.getX() - quadrant.get().getOffset().getX(), playerTile.getY() - quadrant.get().getOffset().getY()));
                return this.biomes.get(quadrant.get().getPos()).getRoom(new TilePosition(playerTile.getX() - quadrant.get().getOffset().getX(), playerTile.getY() - quadrant.get().getOffset().getY()));
            }
        }
        return Optional.empty();
    }

    private Optional<BiomeQuadrant> getBiomeQuadrant(TilePosition tilePos) {
        for (var quadrant : BiomeQuadrant.values()) {
        if (tilePos.getX() >= quadrant.getOffset().getX() && tilePos.getY() >= quadrant.getOffset().getY()
            &&
            tilePos.getX() < quadrant.getOffset().getX() + Biome.SIZE && tilePos.getY() < quadrant.getOffset().getY() + Biome.SIZE
            ) {
                return Optional.of(quadrant);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<GameElement> getRoomElements(Space room) {
        for(var biome : biomes) {
            for (var biomeRoom : biome.getRooms()) {
                if (room.equals(biomeRoom.getValue())) {
                    return biomeRoom.getValue().getElements();
                }
            }
        }
        return List.of();
    }

    @Override
    public List<GameElement> getAllElements() {
        List<GameElement> allEntities = new ArrayList<>();
        for (var biome : biomes) {
            for (var room : biome.getRooms()) {
                allEntities.addAll(room.getValue().getElements());
            }
            for (var corridor : biome.getCorridors()) {
                allEntities.addAll(corridor.getValue().getElements());
            }
        }
        return allEntities;
    }

}