package javawulf.model.map;

public class RoomImpl implements Room {
    private int width;
    private int height;

    public RoomImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

}
