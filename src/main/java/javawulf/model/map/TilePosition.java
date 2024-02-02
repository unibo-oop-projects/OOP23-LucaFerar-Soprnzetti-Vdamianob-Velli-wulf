package javawulf.model.map;

import javafx.util.Pair;

/**
 * TilePosition is a kind of coordinate used to individuate tiles inside a bidimensional space (Map)
 * For example: TilePosition (1,2)  of TILE_A it means that TILE_A is in the second column and the third row of my tile-matrix.
 */
public final class TilePosition extends Pair<Integer, Integer> {

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
