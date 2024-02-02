package javawulf.model.map;

import java.util.List;
import java.util.ArrayList;

import javafx.util.Pair;

/**
 * Implementation of Biome.
 * You can add rooms and corridors in Biome, and you can use 4 biomes to compose
 * a game Map.
 */
public final class BiomeImpl implements Biome {
    private final List<Pair<TilePosition, Space>> rooms = new ArrayList<>();
    private final List<Pair<TilePosition, Space>> corridors = new ArrayList<>();

    @Override
    public Biome addRoom(final TilePosition biomePos, final Room room) {
        this.addSpace(biomePos, room, true);
        return this;
    }

    @Override
    public Biome addCorridor(final TilePosition biomePos, final Corridor corridor) {
        this.addSpace(biomePos, corridor, false);
        return this;
    }

    private Biome addSpace(final TilePosition biomePos, final Space space, boolean isRoom) {
        if (biomePos.getX() + space.getWidth() > Biome.SIZE || biomePos.getY() + space.getHeight() > Biome.SIZE
            || biomePos.getX() < 0 || biomePos.getY() < 0) {
            throw new IllegalArgumentException("Space position & his width " + biomePos + " is out of tile SIZE ("
            + Biome.SIZE + ") biome range");
        }
        Pair<TilePosition, Space> SpaceWithPos = new Pair<>(biomePos, space);
        if (isRoom) {
            this.rooms.add(SpaceWithPos);
        } else {
            this.corridors.add(SpaceWithPos);
        }    return this;
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
