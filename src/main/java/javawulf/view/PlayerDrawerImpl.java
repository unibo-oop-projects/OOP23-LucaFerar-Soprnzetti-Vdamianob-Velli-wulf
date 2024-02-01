package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javawulf.model.player.Player;

/**
 * Implementation of PlayerDrawer.
 */
public class PlayerDrawerImpl implements PlayerDrawer {

    private BufferedImage playerUp;
    private BufferedImage playerDown;
    private BufferedImage playerLeft;
    private BufferedImage playerRight;
    private BufferedImage sword;
    private Player player;

    /**
     * The Player coming from the Controller.
     */
    public PlayerDrawerImpl(Player player) {
        this.player = player;
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
    public void draw(Graphics2D graphics) {
        BufferedImage img = this.sword;
        
        switch (this.player.getDirection()){
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
            case DOWN:
                img = this.playerDown;
                break;
        }

        graphics.drawImage(img, (int) this.player.getBounds().getCollisionArea().getWidth() * GamePanel.scale,
            (int) this.player.getBounds().getCollisionArea().getWidth() * GamePanel.scale, GamePanel.tileSize,
            GamePanel.tileSize, null);
    }
    
}
