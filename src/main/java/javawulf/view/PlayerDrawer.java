package javawulf.view;

import java.awt.Graphics2D;

/**
 * Class used in GamePanel in order to draw the Player character.
 */
public interface PlayerDrawer {

    /**
     * @param graphics where the Player is drawn
     */
    void draw(Graphics2D graphics);

}
