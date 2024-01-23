package javawulf.model.map;

public interface Tile {
    static int TILE_DIMENSION = 24;
    TileType getType();
    void setType(TileType type);
}
