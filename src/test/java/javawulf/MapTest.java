package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import javawulf.model.map.Tile;
import javawulf.model.map.TileType;

public class MapTest {
    @Test void testTiles() {
        Tile firstTile = new TileImpl(TileType.CORRIDOR);
        assertEquals(TileType.CORRIDOR, firstTile.getType());
        firstTile.setType(TileType.ROOM);
        assertEquals(TileType.ROOM, firstTile.getType());
    }
}
