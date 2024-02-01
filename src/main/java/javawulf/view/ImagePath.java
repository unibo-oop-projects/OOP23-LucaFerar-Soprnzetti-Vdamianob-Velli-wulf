package javawulf.view;

public enum ImagePath {
    WALL_TILE("/tiles/wall.png"),
    ROOM_TILE("/tiles/room.png"),
    CENTRAL_ROOM_TILE("/tiles/central_room.png"),
    CORRIDOR_TILE("/tiles/corridor.png"),
    PLAYER_DOWN_1("/player/player_up_1.png"),
    PLAYER_UP("/player/player_up.png"),
    PLAYER_DOWN("/player/player_down.png"),
    PLAYER_LEFT("/player/player_left.png"),
    PLAYER_RIGHT("/player/player_right.png"),
    SWORD("/player/sword.png");

    private final String path;

    ImagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
