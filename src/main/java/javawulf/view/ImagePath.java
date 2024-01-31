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
    /**<img src="../../../resources/player/player_up_1.png"> Player up first sequence.*/
    PLAYER_DOWN_1("/player/player_up_1.png");

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
