package javawulf.view;

import java.awt.Graphics2D;
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

    /**
     * The Player coming from the Controller.
     * 
     * @param player The Player character that must be drawn
     */
    public PlayerDrawerImpl(final Player player) {
        this.player = player;
        this.playerSword = this.player.getSword();
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

        switch (this.player.getDirection()) {
            case UP_RIGHT:
            case RIGHT:
            case DOWN_RIGHT:
                img = this.playerRight; 
                break;
            case UP_LEFT:
            case LEFT:
            case DOWN_LEFT:
                img = this.playerLeft;
                break;
            case UP:
                img = this.playerUp;
                break;
            default:
                img = this.playerDown;
                break;
        }

        graphics.drawImage(img, 
            (int) this.player.getBounds().getCollisionArea().getX() * GamePanel.scale,
            (int) this.player.getBounds().getCollisionArea().getY() * GamePanel.scale,
            GamePanel.tileSize, GamePanel.tileSize, null);

        if (this.playerSword.getBounds().getCollisionType().equals(CollisionType.SWORD)) {
            int width = (int) this.playerSword.getBounds().getCollisionArea().getWidth();
            int height = (int) this.playerSword.getBounds().getCollisionArea().getHeight();
            BufferedImage imgSword = this.sword;
            if (this.playerSword.getSwordType().equals(SwordType.GREATSWORD)) {
                imgSword = this.sword;
            }
            graphics.drawImage(imgSword,
                (int) this.playerSword.getBounds().getCollisionArea().getX() * GamePanel.scale,
                (int) this.playerSword.getBounds().getCollisionArea().getY() * GamePanel.scale,
                width, height, null);
        }
    }

}
