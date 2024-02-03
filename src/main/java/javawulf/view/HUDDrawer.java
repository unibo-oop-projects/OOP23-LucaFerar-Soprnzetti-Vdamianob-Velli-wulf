package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import javawulf.controller.PlayerStatus;

/**
 * Class used in GamePanel in order to draw the HUD.
 */
public final class HUDDrawer extends AbstractDrawer {

    private final GamePanel gamePanel;
    private BufferedImage health;
    private BufferedImage maxHealth;
    private BufferedImage shield;
    private BufferedImage sword;
    private BufferedImage greatsword;
    private final PlayerStatus player;

    /**
     * Creates a new HUDDrawer.
     * 
     * @param player The player character whose status will be made into the HUD
     * @param gamePanel The panel where the HUD must appear in.
     */
    public HUDDrawer(final PlayerStatus player, final GamePanel gamePanel) {
        super(gamePanel, player);
        this.gamePanel = gamePanel;
        this.player = player;
        try {
            this.shield = imageLoader(ImagePath.SHIELD);
            this.health = imageLoader(ImagePath.HEALTH);
            this.maxHealth = imageLoader(ImagePath.MAX_HEALTH);
            this.sword = imageLoader(ImagePath.SWORD);
            this.greatsword = imageLoader(ImagePath.GREATSWORD);
        } catch (IOException e) {
            Logger.getLogger(HUDDrawer.class.getName()).fine(e.getMessage());
        }
    }

    @Override
    public void draw(final Graphics2D graphics) {
        final int x = this.gamePanel.getHeight() / 9 - GamePanel.tileSize / 2;
        final int y = this.gamePanel.getHeight() / 9 - GamePanel.tileSize / 2;

        final int max = this.player.getMaxHealth();
        final int current = this.player.getHealth();
        final int shield = this.player.getShield();
        final String sword = this.player.getSwordType();
        BufferedImage img;
        int i;
        for (i = 0; i < max + shield; i++) {
            if (max <= i) {
                img = this.shield;
            } else if (current > i) {
                img = this.health;
            } else { 
                img = this.maxHealth;
            }
            graphics.drawImage(img, x + img.getWidth() * GamePanel.scale * i,
                    y, GamePanel.tileSize, GamePanel.tileSize, null);
        }
        if ("NORMAL".equals(sword)) {
            img = this.sword;
        } else {
            img = this.greatsword;
        }
        graphics.drawImage(img, x + img.getWidth() * GamePanel.scale * i, y, GamePanel.tileSize,
            GamePanel.tileSize, null);
    }

}
