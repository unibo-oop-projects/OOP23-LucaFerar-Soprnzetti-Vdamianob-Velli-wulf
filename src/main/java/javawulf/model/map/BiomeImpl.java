package javawulf.model.map;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

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
    public Biome addRoom(final TilePosition biomePos, final Space room) {
        this.addSpace(biomePos, room, true);
        return this;
    }

    @Override
    public Biome addCorridor(final TilePosition biomePos, final Space corridor) {
        this.addSpace(biomePos, corridor, false);
        return this;
    }

    private Biome addSpace(final TilePosition biomePos, final Space space, final boolean isRoom) {
        if (biomePos.getX() + space.getWidth() > Biome.SIZE || biomePos.getY() + space.getHeight() > Biome.SIZE
                || biomePos.getX() < 0 || biomePos.getY() < 0) {
            throw new IllegalArgumentException("Space position & his width " + biomePos + " is out of tile SIZE ("
                    + Biome.SIZE + ") biome range");
        }
        Pair<TilePosition, Space> spaceWithPos = new Pair<>(biomePos, space);
        if (isRoom) {
            this.rooms.add(spaceWithPos);
        } else {
            this.corridors.add(spaceWithPos);
        }
        return this;
    }

    @Override
    public ArrayList<Pair<TilePosition, Space>> getRooms() {
        return new ArrayList<>(this.rooms);
    }

    @Override
    public ArrayList<Pair<TilePosition, Space>> getCorridors() {
        return new ArrayList<>(this.corridors);
    }

    @Override
    public Optional<Space> getRoom(final TilePosition tilePos) {
        for (var room : rooms) {
            if (tilePos.getX() >= room.getKey().getX() && tilePos.getY() >= room.getKey().getY()
                    && tilePos.getX() < room.getKey().getX() + room.getValue().getWidth()
                    && tilePos.getY() < room.getKey().getY() + room.getValue().getHeight()) {
                return Optional.of(room.getValue());
            }

        }
        return Optional.empty();
    }
}
