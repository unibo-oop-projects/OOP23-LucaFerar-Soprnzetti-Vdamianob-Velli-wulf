package javawulf.model.map;

import java.util.List;

import java.util.ArrayList;

import javawulf.model.GameElement;

/**
 * A space in the biome. It has a 'width' and a 'height'
 */
public abstract class AbstractSpace implements Space {
    private final int width;
    private final int height;
    private List<GameElement> elements = new ArrayList<>();

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

    @Override
    public void addGameElement(GameElement element) {
        this.elements.add(element);
    }

    @Override
    public List<GameElement> getElements() {
        return this.elements;
    }

}
