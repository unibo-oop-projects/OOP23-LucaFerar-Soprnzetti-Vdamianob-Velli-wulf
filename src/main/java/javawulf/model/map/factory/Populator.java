package javawulf.model.map.factory;

import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.map.BiomeQuadrant;
import javawulf.model.map.Map;
import javawulf.model.map.Space;
import javawulf.model.map.TilePosition;
import javawulf.model.map.TileType;

/**
 * Static class used to populate a map with items, power-up and enemies.
 */
public final class Populator {
    private final int halfTile = TileType.TILE_DIMENSION / 2;

    public Map populate(Map map) {
        for (var biome : map.getBiomes()) {
            for (var room : biome.getRooms()) {
                
            }
        }
        return map;
    }

    /**
     * Utility method used for obtain central coordinate of a space (useful for rooms).
     * @param space
     * @return central position.
     */
    private Coordinate getCentraTilePosition(TilePosition spacePos, Space space, BiomeQuadrant quadrant) {
        final int halfWidth = space.getWidth() / 2;
        final int halfHeight = space.getHeight() / 2;
        return new CoordinateImpl(
            (spacePos.getX() + quadrant.getOffset().getX() + halfWidth) * TileType.TILE_DIMENSION - halfTile, 
            (spacePos.getY() + quadrant.getOffset().getY() + halfHeight) * TileType.TILE_DIMENSION - halfTile);
    }

}
