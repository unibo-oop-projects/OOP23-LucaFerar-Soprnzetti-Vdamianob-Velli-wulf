package javawulf.model.map;

import javafx.util.Pair;

public class Position {
    private final Pair<Integer, Integer> pos;
    public Position(int x, int y) {
        this.pos = new Pair<>(x, y);
    }
    int getX() {
        return this.pos.getKey();
    }
    int getY() {
        return this.pos.getValue();
    }
}
