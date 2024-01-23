package javawulf;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javawulf.model.map.Tile;
import javawulf.model.map.TileImpl;
import javawulf.model.map.TileType;

public class MapTest {
    @Test void testTiles() {
        Tile firstTile = new TileImpl(TileType.CORRIDOR);
        assertEquals(TileType.CORRIDOR, firstTile.getType());
        assertTrue(firstTile.getType().isCrossable());
        // firstTile.setType(TileType.ROOM);
        // assertEquals(TileType.ROOM, firstTile.getType());

        TileType tipo = TileType.CORRIDOR;
        tipo = TileType.ROOM;
    }
}
