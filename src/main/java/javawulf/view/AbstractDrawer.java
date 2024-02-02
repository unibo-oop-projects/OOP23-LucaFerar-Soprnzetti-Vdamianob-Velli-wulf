package javawulf.view;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javawulf.model.player.Player;

public abstract class AbstractDrawer implements Drawer {

    private final GamePanel gamePanel;

    public AbstractDrawer(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    protected BufferedImage imageLoader(final ImagePath image) throws IOException {
        return ImageIO.read(getClass().getResourceAsStream(image.getPath()));
    }

    protected int getPlayerX(){
        return this.gamePanel.getWidth() / 2 - Player.OBJECT_SIZE / 2;
    }

    protected int getPlayerY(){
        return this.gamePanel.getHeight() / 2 - Player.OBJECT_SIZE / 2;
    }

    protected BufferedImage rotateImage(final BufferedImage startingImage, final String direction) {
        AffineTransform tx = new AffineTransform();
        AffineTransformOp op;
        BufferedImage finalImage = startingImage;
        double theta = 0;

        if (direction.equals("up")) {
            theta = 0;
        } else if (direction.equals("down")) {
            theta = Math.PI;
        } else if (direction.equals("right")) {
            theta = Math.PI / 2;
        } else {
            theta = -Math.PI / 2;
        }
        tx.rotate(theta, finalImage.getWidth() / 2, finalImage.getHeight() / 2);
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(finalImage, null);
    }
}
