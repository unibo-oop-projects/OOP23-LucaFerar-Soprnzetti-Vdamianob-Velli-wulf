package javawulf.view;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javawulf.model.player.Player;

/**
 * Class that implements useful methods for all Drawer classes.
 */
public abstract class AbstractDrawer implements Drawer {

    private final GamePanel gamePanel;

    /**
     * @param gamePanel The gamepanel the game must be drawn into.
     */
    public AbstractDrawer(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * It loades an image from the resources.
     * 
     * @param image The image file to be loaded
     * @return The file as a BufferedImage
     */
    protected BufferedImage imageLoader(final ImagePath image) throws IOException {
        return ImageIO.read(getClass().getResourceAsStream(image.getPath()));
    }

    /**
     * @return The X-axis position of Player on the screen.
     */
    protected int getPlayerX() {
        return this.gamePanel.getWidth() / 2 - Player.OBJECT_SIZE / 2;
    }

    /**
     * @return The Y-axis position of Player on the screen.
     */
    protected int getPlayerY() {
        return this.gamePanel.getHeight() / 2 - Player.OBJECT_SIZE / 2;
    }

    /**
     * Rotates an image according to the String direction.
     * 
     * @param startingImage The image that must be rotated
     * @param direction The direction the image must face.
     * @return The image rotated 
     */
    protected BufferedImage rotateImage(final BufferedImage startingImage, final String direction) {
        final AffineTransform tx = new AffineTransform();
        AffineTransformOp op;
        final BufferedImage finalImage = startingImage;
        double theta;

        if ("up".equals(direction)) {
            theta = 0;
        } else if ("down".equals(direction)) {
            theta = Math.PI;
        } else if ("right".equals(direction)) {
            theta = Math.PI / 2;
        } else {
            theta = -Math.PI / 2;
        }
        tx.rotate(theta, finalImage.getWidth() / 2, finalImage.getHeight() / 2);
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(finalImage, null);
    }
}
