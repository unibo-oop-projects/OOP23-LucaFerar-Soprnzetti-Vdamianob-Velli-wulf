package javawulf.view;

import java.awt.Graphics2D;
import java.util.List;

/**
 * Class used in GamePanel in order to draw the Player character.
 */
public interface PlayerDrawer {
    
    /**
     * @param graphics where the Player is drawn
     */
    public void draw(Graphics2D graphics, int x, int y, List<Boolean> directions, boolean attack);
}
