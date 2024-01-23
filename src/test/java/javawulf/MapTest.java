package javawulf;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javawulf.model.map.Room;
import javawulf.model.map.RoomImpl;
import javawulf.model.map.Tile;
import javawulf.model.map.TileImpl;
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
        Room roomA = new RoomImpl(10, 10);
        assertEquals(10, roomA.getWidth());
        assertEquals(10, roomA.getHeight());

        Room roomB = new RoomImpl(6, 9);
        assertEquals(6, roomB.getWidth());
        assertEquals(9, roomB.getHeight());
    }
}
