package javawulf;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javawulf.model.map.Biome;
import javawulf.model.map.BiomeImpl;
import javawulf.model.map.Corridor;
import javawulf.model.map.Room;
import javawulf.model.map.Space;
import javawulf.model.map.Tile;
import javawulf.model.map.TileImpl;
import javawulf.model.map.TilePosition;
import javawulf.model.map.TileType;

public class MapTest {
    @Test
    void testTiles() {
        Tile firstTile = new TileImpl(TileType.CORRIDOR);
        assertEquals(TileType.CORRIDOR, firstTile.getType());
        assertTrue(firstTile.getType().isCrossable());

        Tile secondTile = new TileImpl(TileType.WALL);
        assertEquals(TileType.WALL, secondTile.getType());
        assertFalse(secondTile.getType().isCrossable());
    }

    @Test
    void testRooms() {
        assertEquals(TileType.ROOM, Room.defaultType);

        Space roomA = new Room(10, 10);
        assertEquals(10, roomA.getWidth());
        assertEquals(10, roomA.getHeight());

        Space roomB = new Room(6, 9);
        assertEquals(6, roomB.getWidth());
        assertEquals(9, roomB.getHeight());
    }

    @Test
    void testCorridors() {
        assertEquals(TileType.CORRIDOR, Corridor.defaultType);

        Space corridor1 = new Corridor(10, 5);
        assertEquals(10, corridor1.getWidth());
        assertEquals(5, corridor1.getHeight());

        Space corridor2 = new Corridor(6, 3);
        assertEquals(6, corridor2.getWidth());
        assertEquals(3, corridor2.getHeight());
    }

    @Test
    void testBiomes() {
        Biome firstBiome = new BiomeImpl();
        firstBiome.addRoom(new TilePosition(5, 5), new Room(10, 10));
        firstBiome.addRoom(new TilePosition(10, 5), new Room(7, 6));
        
        assertEquals(2, firstBiome.getRooms().size());
        assertEquals(0, firstBiome.getCorridors().size());

        assertThrows(IllegalArgumentException.class, () -> firstBiome.addRoom(new TilePosition(200, 500), new Room(5, 4)));
    }
}
