package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javawulf.model.player.Player;
import javawulf.model.player.Sword.SwordType;

public class HUDDrawerImpl implements HUDDrawer {

    private GamePanel gamePanel;
    private BufferedImage health;
    private BufferedImage maxHealth;
    private BufferedImage shield;
    private BufferedImage sword;
    private BufferedImage greatsword;
    private Player player;
    
    public HUDDrawerImpl(final Player player, final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.player = player;
        try {
            this.shield = ImageIO.read(getClass().getResourceAsStream(ImagePath.SHIELD.getPath()));
            this.health = ImageIO.read(getClass().getResourceAsStream(ImagePath.HEALTH.getPath()));
            this.maxHealth = ImageIO.read(getClass().getResourceAsStream(ImagePath.MAX_HEALTH.getPath()));
            this.sword = ImageIO.read(getClass().getResourceAsStream(ImagePath.SWORD.getPath()));
            this.greatsword = ImageIO.read(getClass().getResourceAsStream(ImagePath.GREATSWORD.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        int x = this.gamePanel.getHeight() / 9 - Player.OBJECT_SIZE / 2;
        int y = this.gamePanel.getHeight() / 9 - Player.OBJECT_SIZE / 2;

        int max = this.player.getPlayerHealth().getMaxHealth();
        int current = this.player.getPlayerHealth().getHealth();
        int shield = this.player.getPlayerHealth().getShieldStatus().getStrength();
        int sword = this.player.getSword().getSwordType() == SwordType.NORMAL ? 1 : 2;
        BufferedImage img;
        int i;
        for (i = 0; i < max + shield; i++) {
            if (max <= i) {
                img = this.shield;
            } else if (current > i + 1) {
                img = this.health;
            } else { 
                img = this.maxHealth;
            }
            graphics.drawImage(img, x + img.getWidth() * GamePanel.scale * i,
                    y, GamePanel.tileSize, GamePanel.tileSize, null);
        }
        if (sword == 1) {
            img = this.sword;
        } else {
            img = this.greatsword;
        }
        graphics.drawImage(img, x + img.getWidth() * GamePanel.scale * i,
                y, GamePanel.tileSize, GamePanel.tileSize, null);
    }
    
}
