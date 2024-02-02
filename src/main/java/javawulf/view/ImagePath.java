package javawulf.view;

/**
 * Each element of this enum contains a relative path that can be used to reach an image of a specific *View* element.
 * Images are stored in /resources directory
 */
public enum ImagePath {
    /**<img src="../../../resources/tiles/wall.png"> Wall tile.*/
    WALL_TILE("/tiles/wall.png"),
    /**<img src="../../../resources/tiles/room.png"> Room tile.*/
    ROOM_TILE("/tiles/room.png"),
    /**<img src="../../../resources/tiles/central_room.png"> Central room tile.*/
    CENTRAL_ROOM_TILE("/tiles/central_room.png"),
    /**<img src="../../../resources/tiles/corridor.png"> Corridor tile.*/
    CORRIDOR_TILE("/tiles/corridor.png"),
    /**<img src="../../../resources/player/player_up.png"> Player up.*/
    PLAYER_UP("/player/player_up.png"),
    /**<img src="../../../resources/player/player_down.png"> Player down.*/
    PLAYER_DOWN("/player/player_down.png"),
    /**<img src="../../../resources/player/player_left.png"> Player left.*/
    PLAYER_LEFT("/player/player_left.png"),
    /**<img src="../../../resources/player/player_right.png"> Player right.*/
    PLAYER_RIGHT("/player/player_right.png"),
    /**<img src="../../../resources/player/player/sword.png"> Sword.*/
    SWORD("/player/sword.png"),

    GREATSWORD("/player/greatsword.png"),

    HEALTH("/player/health.png"),

    MAX_HEALTH("/player/recoverable_health.png"),

    SHIELD("/player/shield.png");

    private final String path;

    ImagePath(final String path) {
        this.path = path;
    }

    /**
     * 
     * @return the path of the image related to the element.
     */
    public String getPath() {
        return this.path;
    }
}
