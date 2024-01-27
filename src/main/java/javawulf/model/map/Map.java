package javawulf.model.map;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;

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
     * Width-tile dimension of the central biome
     */
    public static int WIDTH_CENTRAL_BIOME = 5;

    /**
     * Tile-dimension of the whole map
     */
    public static final int MAP_SIZE = Biome.SIZE * 2 + WIDTH_CENTRAL_BIOME;

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
     * @return a Set of tiletypes that the given boundingbox is intersecting (an
     *         empty Set if it is
     *         Out of map)
     *         <img src="../../../../resources/javadoc/intersections.gif" />
     * In this GIF, in a first moment the entity (red square) is completely inside a room, so will be return Set = { ROOM }.
     * In a second time, the entity is between room tiles and wall tiles: will be return Set = { ROOM, WALL }.
     */
    Set<TileType> getTileTypes(BoundingBox boundBoxEntity);

    HashMap<TilePosition, TileType> getTilesMap();
}
