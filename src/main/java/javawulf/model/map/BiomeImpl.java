package javawulf.model.map;

import java.util.List;
import java.util.ArrayList;

import javafx.util.Pair;

/**
 * Implementation of Biome.
 * You can add rooms and corridors in Biome, and you can use 4 biomes to compose a game Map.
 */
public class BiomeImpl implements Biome {
    private final List<Pair<TilePosition, Space>> rooms;
    private final List<Pair<TilePosition, Space>> corridors;

    public BiomeImpl() {
        this.rooms = new ArrayList<>();
        this.corridors = new ArrayList<>();
    }

    @Override
    public Biome addRoom(TilePosition biomePos, Room room) {
        if (biomePos.getX() + room.getWidth() > Biome.SIZE || biomePos.getY() + room.getHeight() > Biome.SIZE
        || biomePos.getX() < 0 || biomePos.getY() < 0) {
            throw new IllegalArgumentException("Room position & his width" + biomePos + " is out of tile SIZE ("+Biome.SIZE+") biome range");
        }
        this.rooms.add(new Pair<TilePosition, Space>(biomePos, room));
        return this;
    }

    @Override
    public Biome addCorridor(TilePosition biomePos, Corridor corridor) {
        if (biomePos.getX() + corridor.getWidth() > Biome.SIZE || biomePos.getY() + corridor.getHeight() > Biome.SIZE
        || biomePos.getX() < 0 || biomePos.getY() < 0) {
            throw new IllegalArgumentException("Corridor position & his width " + biomePos + " is out of tile SIZE (" + Biome.SIZE + ") biome range");
        }
        this.corridors.add(new Pair<TilePosition, Space>(biomePos, corridor));
        return this;
    }

    @Override
    public List<Pair<TilePosition, Space>> getRooms() {
        return new ArrayList<>(this.rooms);
    }

    @Override
    public List<Pair<TilePosition, Space>> getCorridors() {
        return new ArrayList<>(this.corridors);
    }

}
