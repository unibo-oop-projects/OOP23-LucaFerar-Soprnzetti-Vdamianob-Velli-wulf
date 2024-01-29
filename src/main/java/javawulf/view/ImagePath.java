package javawulf.view;

public enum ImagePath {
    WALL_TILE("/tiles/wall.png"),
    ROOM_TILE("/tiles/room.png"),
    CORRIDOR_TILE("/tiles/corridor.png"),
    PLAYER_DOWN_1("/player/player_up_1.png");

    private final String path;

    ImagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
