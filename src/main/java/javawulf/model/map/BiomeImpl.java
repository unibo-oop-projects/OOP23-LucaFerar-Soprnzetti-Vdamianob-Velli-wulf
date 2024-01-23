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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRoom'");
    }

    @Override
    public void addCorridor(Pair<Integer, Integer> biomePos, Corridor corridor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCorridor'");
    }

    @Override
    public List<Pair<Pair<Integer, Integer>, Room>> getRooms() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRooms'");
    }

    @Override
    public List<Pair<Pair<Integer, Integer>, Room>> getCorridors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCorridors'");
    }

}
