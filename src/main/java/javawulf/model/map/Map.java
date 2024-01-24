package javawulf.model.map;

import javawulf.model.PositionOnMap;

/**
 * Map is the complete bidimensional game Enviroment. It takes four biomes and it uses their to build the enviroment.
 * Enviroment will be:
 * <img src="../../../../resources/javadoc/biome-map-suddivision.png" />
 */
public interface Map {
    TilePosition getTilePosition(PositionOnMap position);
    TileType getTileType(PositionOnMap position);
}
