package javawulf.controller;

import javawulf.model.map.Map;

/**
 * Utility class used for print Map informations.
 */
public final class LogInfo {

    private LogInfo() {
        throw new UnsupportedOperationException("This class cannot be instantiated (Utility class)");
    }

    /**
     * 
     * @param map of current game match
     */
    public static void print(Map map) {
        if(map.getPlayerRoom().isPresent()) {
            System.out.println("GameObjects stanza corrente: " + map.getRoomElements(map.getPlayerRoom().get()));
        }
    }
}
