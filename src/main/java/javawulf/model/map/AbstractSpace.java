package javawulf.model.map;

import java.util.List;

import java.util.ArrayList;

import java.util.Collection;

import javawulf.model.GameElement;

/**
 * A space in the biome. It has a 'width' and a 'height' (in tiles)
 * Width and height are relative to the biome where space will be included, not to the whole map.
 * In spaces is possible to add entities.
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
    public Space addGameElement(GameElement element) {
        this.elements.add(element);
        return this;
    }

    @Override
    public Space addGameElements(Collection<GameElement> elements) {
        this.elements.addAll(elements);
        return this;
    }

    @Override
    public List<GameElement> getElements() {
        return this.elements;
    }

    @Override
    public String toString() {
        return "AbstractSpace [width=" + width + ", height=" + height + ", elements=" + elements + "]";
    }

}
