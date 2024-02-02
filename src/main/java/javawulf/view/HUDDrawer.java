package javawulf.view;

import java.awt.Graphics2D;

/**
 * Class used in GamePanel in order to draw the HUD.
 */
public interface HUDDrawer {

    /**
     * @param graphics where the HUD is drawn
     */
    void draw(Graphics2D graphics);
}
