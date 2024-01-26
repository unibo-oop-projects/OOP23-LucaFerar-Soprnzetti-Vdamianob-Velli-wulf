package javawulf.model.map;

import javafx.util.Pair;

public class TilePosition extends Pair<Integer, Integer> {

    public TilePosition(Integer tileX, Integer tileY) {
        super(tileX, tileY);
    }

    public Integer getX() {
        return this.getKey();
    }

    public Integer getY() {
        return this.getValue();
    }

    @Override
    public String toString() {
        return "TilePosition [x:"+getX()+" y:"+getY()+"]";
    }

}
