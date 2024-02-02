package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;
import javawulf.model.player.Sword;
import javawulf.model.player.Sword.SwordType;

/**
 * Implementation of PlayerDrawer.
 */
public final class PlayerDrawer extends AbstractDrawer {

    private BufferedImage playerUp;
    private BufferedImage playerDown;
    private BufferedImage playerLeft;
    private BufferedImage playerRight;
    private BufferedImage sword;
    private BufferedImage greatsword;
    private Player player;
    private Sword playerSword;

    /**
     * The Player coming from the Controller.
     * 
     * @param player The Player character that must be drawn
     * @param gamePanel The panel that must be updated
     */
    public PlayerDrawer(final Player player, final GamePanel gamePanel) {
        super(gamePanel);
        this.player = player;
        this.playerSword = this.player.getSword();
        try {
            this.playerUp = imageLoader(ImagePath.PLAYER_UP);
            this.playerDown = imageLoader(ImagePath.PLAYER_DOWN);
            this.playerLeft = imageLoader(ImagePath.PLAYER_LEFT);
            this.playerRight = imageLoader(ImagePath.PLAYER_RIGHT);
            this.sword = imageLoader(ImagePath.SWORD);
            this.greatsword = imageLoader(ImagePath.GREATSWORD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        BufferedImage img = this.sword;
        BufferedImage imgSword = this.sword;
        String direction;

        switch (this.player.getDirection()) {
            case UP_RIGHT:
            case RIGHT:
            case DOWN_RIGHT:
                img = this.playerRight;
                direction = "right";
                break;
            case UP_LEFT:
            case LEFT:
            case DOWN_LEFT:
                img = this.playerLeft;
                direction = "left";
                break;
            case UP:
                img = this.playerUp;
                direction = "up";
                break;
            default:
                img = this.playerDown;
                direction = "down";
                break;
        }

        int playerX = getPlayerX();
        int playerY = getPlayerY();

        graphics.drawImage(img, playerX, playerY,
            GamePanel.tileSize, GamePanel.tileSize, null);

        if (this.playerSword.getBounds().getCollisionType().equals(CollisionType.SWORD)) {
            int width = (int) this.playerSword.getBounds().getCollisionArea().getWidth();
            int height = (int) this.playerSword.getBounds().getCollisionArea().getHeight();
            if (this.playerSword.getSwordType().equals(SwordType.GREATSWORD)) {
                imgSword = this.greatsword;
            }
            imgSword = rotateImage(imgSword, direction);

            int swordX = playerX - (int) (this.player.getBounds().getCollisionArea().getX()
                - this.player.getSword().getBounds().getCollisionArea().getX()) * GamePanel.scale;
            int swordY = playerY - (int) (this.player.getBounds().getCollisionArea().getY()
                - this.player.getSword().getBounds().getCollisionArea().getY()) * GamePanel.scale;
            graphics.drawImage(imgSword, swordX, swordY, width * GamePanel.scale, height * GamePanel.scale, null);
        }
    }

}
