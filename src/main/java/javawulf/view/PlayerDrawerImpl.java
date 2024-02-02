package javawulf.view;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;
import javawulf.model.player.Sword;
import javawulf.model.player.Sword.SwordType;

/**
 * Implementation of PlayerDrawer.
 */
public final class PlayerDrawerImpl implements PlayerDrawer {

    private BufferedImage playerUp;
    private BufferedImage playerDown;
    private BufferedImage playerLeft;
    private BufferedImage playerRight;
    private BufferedImage sword;
    private Player player;
    private Sword playerSword;
    private GamePanel gamePanel;

    /**
     * The Player coming from the Controller.
     * 
     * @param player The Player character that must be drawn
     */
    public PlayerDrawerImpl(final Player player, GamePanel gamePanel) {
        this.player = player;
        this.playerSword = this.player.getSword();
        this.gamePanel = gamePanel;
        try {
            this.playerUp = ImageIO.read(getClass().getResourceAsStream(ImagePath.PLAYER_UP.getPath()));
            this.playerDown = ImageIO.read(getClass().getResourceAsStream(ImagePath.PLAYER_DOWN.getPath()));
            this.playerLeft = ImageIO.read(getClass().getResourceAsStream(ImagePath.PLAYER_LEFT.getPath()));
            this.playerRight = ImageIO.read(getClass().getResourceAsStream(ImagePath.PLAYER_RIGHT.getPath()));
            this.sword = ImageIO.read(getClass().getResourceAsStream(ImagePath.SWORD.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        BufferedImage img = this.sword;
        BufferedImage imgSword = this.sword;

        switch (this.player.getDirection()) {
            case UP_RIGHT:
            case RIGHT:
            case DOWN_RIGHT:
                img = this.playerRight;
                imgSword = rotateImage(imgSword, "right");
                break;
            case UP_LEFT:
            case LEFT:
            case DOWN_LEFT:
                img = this.playerLeft;
                imgSword = rotateImage(imgSword, "left");
                break;
            case UP:
                img = this.playerUp;
                imgSword = rotateImage(imgSword, "up");
                break;
            default:
                img = this.playerDown;
                imgSword = rotateImage(imgSword, "down");
                break;
        }

        int playerX = this.gamePanel.getWidth()/2 - Player.OBJECT_SIZE/2;
        int playerY = this.gamePanel.getHeight()/2 - Player.OBJECT_SIZE/2;

        graphics.drawImage(img, playerX, playerY,
            GamePanel.tileSize, GamePanel.tileSize, null);

        if (this.playerSword.getBounds().getCollisionType().equals(CollisionType.SWORD)) {
            int width = (int) this.playerSword.getBounds().getCollisionArea().getWidth();
            int height = (int) this.playerSword.getBounds().getCollisionArea().getHeight();
            if (this.playerSword.getSwordType().equals(SwordType.GREATSWORD)) {
                imgSword = this.sword;
            }

            int swordX = playerX - (this.player.getPosition().getX() - this.player.getSword().getPosition().getX())
                * GamePanel.scale;
            int swordY = playerY - (this.player.getPosition().getY() - this.player.getSword().getPosition().getY())
                * GamePanel.scale;
            graphics.drawImage(imgSword, swordX, swordY, width * GamePanel.scale, height * GamePanel.scale, null);
        }
    }

    private BufferedImage rotateImage(BufferedImage startingImage, String direction) {
        AffineTransform tx = new AffineTransform();
        AffineTransformOp op;
        BufferedImage finalImage = startingImage;
        double theta=0;

        if (direction.equals("up")) {
            theta = 0;
        } else if (direction.equals("down")) {
            theta = Math.PI;
        } else if (direction.equals("right")) {
            theta = Math.PI/2;
        } else {
            theta = -Math.PI/2;
        }
        tx.rotate(theta, finalImage.getWidth() / 2, finalImage.getHeight() / 2);
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(finalImage, null);
    }

}
