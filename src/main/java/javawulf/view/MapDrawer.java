package javawulf.view;

import java.awt.Graphics2D;

/**
 * Used in GamePanel for drawing Map (Game World)
 */
public interface MapDrawer {
    /**
     * 
     * @param graphics where the map is drawn
     */
    public void draw(Graphics2D graphics);
}
