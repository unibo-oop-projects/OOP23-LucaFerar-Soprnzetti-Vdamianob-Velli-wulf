package javawulf.model.map.factory;

import javawulf.model.map.Map;
import javawulf.model.player.Player;

public interface MapFactory {
    /**
     * 
     * @return a default map. Optimized for 20 tile-size biomes
     */
    Map getDefaultMap1(Player player);

    Map getTestMap(Player player);
}
