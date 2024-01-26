package javawulf.model.map;

import java.util.Optional;
import java.util.Set;

import javawulf.model.BoundingBox;
import javawulf.model.Coordinate;

/**
 * Map is the complete bidimensional game Enviroment. It takes four biomes and
 * it uses their to build the enviroment.
 * Enviroment will be:
 * <img src="../../../../resources/javadoc/biome-map-suddivision.png" />
 * 
 * So Map will behave like a builder of the final Enviroment of the game.
 */
public interface Map {
    /**
     * 
     * @param position (absolute)
     * @return tile position (empty if the given position is Out of map)
     */
    Optional<TilePosition> getTilePosition(Coordinate position);

    /**
     * 
     * @param position (absolute)
     * @return tile type relative to the given position (empty if the position is
     *         Out of map)
     */
    Optional<TileType> getTileType(Coordinate position);

    /**
     * 
     * @param boundBoxEntity to calculate
     * @return a Set of tiletypes that the given boundingbox is intersecting (an empty Set if it is
     *         Out of map)
     * <img src="../../../../resources/javadoc/intersections.gif" />
     */
    Set<TileType> getTileTypes(BoundingBox boundBoxEntity);
}
