package javawulf.model.map;

public enum TileType {
    WALL(false),
    ROOM(true),
    DOOR(true),
    CORRIDOR(true);
    
    private final boolean crossable;

    TileType(boolean crossable) {
        this.crossable = crossable;
    }

    public boolean isCrossable() {
        return this.crossable;
    }
}
