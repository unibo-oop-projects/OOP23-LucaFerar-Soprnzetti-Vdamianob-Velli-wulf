package javawulf.model.map;

import javawulf.model.PositionOnMap;

/**
 * Map is the complete bidimensional game Enviroment. It takes four biomes and
 * it uses their to build the enviroment.
 * Enviroment will be:
 * <img src="../../../../resources/javadoc/biome-map-suddivision.png" />
 */
public interface Map {
    /**
     * 
     * @param position (absolute)
     * @return tile position
     */
    TilePosition getTilePosition(PositionOnMap position);

    /**
     * 
     * @param position (absolute)
     * @return tile type relative to the position passed
     */
    TileType getTileType(PositionOnMap position);
}
