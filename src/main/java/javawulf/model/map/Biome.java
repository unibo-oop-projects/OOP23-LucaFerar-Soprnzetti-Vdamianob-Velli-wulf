package javawulf.model.map;

import javafx.util.Pair;
import java.util.List;

/**
 * Biome rapresent a macro-part of a Map (in the case of this game, biome is a
 * quadrant ¼ of the entire gamemap)
 * Each quadrant should have a single hamulet piece. Biomes should connected to
 * each other.
 * It can be consider a bidimensional container of Rooms and Corridors.
 */
public interface Biome {
    /**
     * Size (in tile) of biomes. All biomes have same sizes and they are square
     * (Size repesents
     * both Height and Width).
     */
    public static int SIZE = 50;

    /**
     * 
     * @param biomePos is the up-left angle position of the room and represents
     *                 where will be
     *                 placed inside the biome (tile x, y)
     * @param room     to add to the Biome
     */
    void addRoom(Pair<Integer, Integer> biomePos, Room room);

    /**
     * 
     * @param biomePos is the up-left angle position of the corridor and represents
     *                 where will be
     *                 placed inside the biome (tile x, y)
     * @param corridor to add to the Biome
     */
    void addCorridor(Pair<Integer, Integer> biomePos, Corridor corridor);

    /**
     * 
     * @return a defensive copy of a list of room positions and their relative rooms
     *         [ (tile x, y) - ROOM ]
     */
    List<Pair<Pair<Integer, Integer>, Room>> getRooms();

    /**
     * 
     * @return a defensive copy of a list of corridor positions and their relative
     *         corridors [ (tile x, y) - CORRIDOR ]
     */
    List<Pair<Pair<Integer, Integer>, Room>> getCorridors();

}
