package javawulf.model.map.factory;

import javawulf.model.map.Map;

public interface MapFactory {
    /**
     * 
     * @return a default map. Optimized for 20 tile-size biomes
     */
    Map getDefaultMap1();

    Map getTestMap();
}
