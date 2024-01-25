package javawulf.model.map;

public enum TileType {
    WALL(false),
    ROOM(true),
    DOOR(true),
    CORRIDOR(true);
    
    private final boolean crossable;
    static int TILE_DIMENSION = 24;

    TileType(boolean crossable) {
        this.crossable = crossable;
    }

    public boolean isCrossable() {
        return this.crossable;
    }
}
