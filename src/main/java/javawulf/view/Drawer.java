package javawulf.view;

import java.awt.Graphics2D;

/**
 * Class used in GamePanel in order to draw the elements of the game.
 */
public interface Drawer {

    /**
     * @param graphics where the HUD is drawn
     */
    void draw(Graphics2D graphics);
}
