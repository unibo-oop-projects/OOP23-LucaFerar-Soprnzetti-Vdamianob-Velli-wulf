package javawulf.view;

import java.awt.Graphics2D;

/**
 * Singleton class to use in GamePanel for drawing Map (Game World)
 */
public interface MapDrawer {
    /**
     * 
     * @param graphics where draw the map
     */
    public void draw(Graphics2D graphics);
}
