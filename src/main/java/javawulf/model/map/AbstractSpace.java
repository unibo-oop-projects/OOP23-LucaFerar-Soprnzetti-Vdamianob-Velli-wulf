package javawulf.model.map;

public abstract class AbstractSpace implements Space {
    private final int width;
    private final int height;

    public AbstractSpace(int width, int height) {
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
