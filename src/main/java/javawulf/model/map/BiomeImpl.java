package javawulf.model.map;

import java.util.List;
import java.util.ArrayList;

import javafx.util.Pair;

public class BiomeImpl implements Biome {
    private final List<Pair<Pair<Integer, Integer>, Room>> rooms;
    private final List<Pair<Pair<Integer, Integer>, Corridor>> corridors;

    public BiomeImpl() {
        this.rooms = new ArrayList<>();
        this.corridors = new ArrayList<>();
    }

    @Override
    public void addRoom(Pair<Integer, Integer> biomePos, Room room) {
        this.rooms.add(new Pair<Pair<Integer, Integer>, Room>(biomePos, room));
    }

    @Override
    public void addCorridor(Pair<Integer, Integer> biomePos, Corridor corridor) {
        this.corridors.add(new Pair<Pair<Integer, Integer>, Corridor>(biomePos, corridor));
    }

    @Override
    public List<Pair<Pair<Integer, Integer>, Room>> getRooms() {
        return new ArrayList<>(this.rooms);
    }

    @Override
    public List<Pair<Pair<Integer, Integer>, Corridor>> getCorridors() {
        return new ArrayList<>(this.corridors);
    }

}
