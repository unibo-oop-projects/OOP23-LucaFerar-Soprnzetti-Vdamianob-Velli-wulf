package javawulf;

import javawulf.model.CoordinateImpl;
import javawulf.model.map.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MapFactoryTest {

    @Test
    void testDefaultMap()
    {
        Map def1Map = new MapFactoryImpl().defaultMap1();
        assertEquals(TileType.WALL, def1Map.getTileType(new CoordinateImpl(0, 0)).get());
        assertEquals(TileType.ROOM, def1Map.getTileType(new CoordinateImpl(5*TileType.TILE_DIMENSION, 5*TileType.TILE_DIMENSION)).get());
        assertEquals(TileType.ROOM, def1Map.getTileType(new CoordinateImpl(6*TileType.TILE_DIMENSION, 6*TileType.TILE_DIMENSION)).get());
    }

}
