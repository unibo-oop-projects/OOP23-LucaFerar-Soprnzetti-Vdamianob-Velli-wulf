package javawulf.model.map;

import java.util.Optional;

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
     * @return tile type relative to the given position (empty if the position is Out of map)
     */
    Optional<TileType> getTileType(Coordinate position);
}
