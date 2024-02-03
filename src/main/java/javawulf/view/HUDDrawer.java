package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javawulf.model.player.Player;
import javawulf.model.player.Sword.SwordType;

/**
 * Class used in GamePanel in order to draw the HUD.
 */
public final class HUDDrawer extends AbstractDrawer {

    private GamePanel gamePanel;
    private BufferedImage health;
    private BufferedImage maxHealth;
    private BufferedImage shield;
    private BufferedImage sword;
    private BufferedImage greatsword;
    private Player player;

    /**
     * Creates a new HUDDrawer.
     * 
     * @param player The player character whose status will be made into the HUD
     * @param gamePanel The panel where the HUD must appear in.
     */
    public HUDDrawer(final Player player, final GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.player = player;
        try {
            this.shield = imageLoader(ImagePath.SHIELD);
            this.health = imageLoader(ImagePath.HEALTH);
            this.maxHealth = imageLoader(ImagePath.MAX_HEALTH);
            this.sword = imageLoader(ImagePath.SWORD);
            this.greatsword = imageLoader(ImagePath.GREATSWORD);
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
        graphics.drawImage(img, x + img.getWidth() * GamePanel.scale * i, y, GamePanel.tileSize,
            GamePanel.tileSize, null);
    }

}
