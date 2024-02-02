package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.player.Player;
import javawulf.model.player.Sword;
import javawulf.model.player.Sword.SwordType;

/**
 * Class used to draw Player.
 */
public final class PlayerDrawer extends AbstractDrawer {

    private BufferedImage player;
    private BufferedImage sword;
    private BufferedImage greatsword;
    private Player gamePlayer;
    private Sword playerSword;

    /**
     * The Player coming from the Controller.
     * 
     * @param player The Player character that must be drawn
     * @param gamePanel The panel that must be updated
     */
    public PlayerDrawer(final Player player, final GamePanel gamePanel) {
        super(gamePanel);
        this.gamePlayer = player;
        this.playerSword = this.gamePlayer.getSword();
        try {
            this.player = imageLoader(ImagePath.PLAYER_UP);
            this.sword = imageLoader(ImagePath.SWORD);
            this.greatsword = imageLoader(ImagePath.GREATSWORD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        BufferedImage img = this.player;
        BufferedImage imgSword = this.sword;
        String direction;

        switch (this.gamePlayer.getDirection()) {
            case UP_RIGHT:
            case RIGHT:
            case DOWN_RIGHT:
                direction = "right";
                break;
            case UP_LEFT:
            case LEFT:
            case DOWN_LEFT:
                direction = "left";
                break;
            case UP:
                direction = "up";
                break;
            default:
                direction = "down";
                break;
        }

        img = rotateImage(img, direction);

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

            int swordX = playerX - (int) (this.gamePlayer.getBounds().getCollisionArea().getX()
                - this.playerSword.getBounds().getCollisionArea().getX()) * GamePanel.scale;
            int swordY = playerY - (int) (this.gamePlayer.getBounds().getCollisionArea().getY()
                - this.playerSword.getBounds().getCollisionArea().getY()) * GamePanel.scale;
            graphics.drawImage(imgSword, swordX, swordY, width * GamePanel.scale, height * GamePanel.scale, null);
        }
    }

}
