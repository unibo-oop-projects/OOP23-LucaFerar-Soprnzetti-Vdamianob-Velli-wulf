package javawulf.view;

import java.awt.Graphics2D;

/**
 * Class used in GamePanel to draw the Pawn enemy.
 */
public interface PawnDrawer {
    
    /**
     * @param graphics where the Pawn is drawn
     */
    void draw(Graphics2D graphics);
}
