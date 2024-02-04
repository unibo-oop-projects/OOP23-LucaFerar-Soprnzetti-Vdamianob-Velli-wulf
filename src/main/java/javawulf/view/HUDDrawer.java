package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import javawulf.controller.PlayerStatus;
import javawulf.model.map.TileType;
import java.awt.Color;
import java.awt.Font;

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
        final int upperCorner = this.gamePanel.getWidth() / 2 - (18 * TileType.TILE_DIMENSION * GamePanel.SCALE) / 2;
        final int leftCorner = this.gamePanel.getHeight() / 2 - (16 * TileType.TILE_DIMENSION * GamePanel.SCALE) / 2;
        final int lowerCorner = this.gamePanel.getWidth() / 2 + (14 * TileType.TILE_DIMENSION * GamePanel.SCALE) / 2;
        final int rightCorner = this.gamePanel.getHeight() / 2 + (14 * TileType.TILE_DIMENSION * GamePanel.SCALE) / 2;
        this.drawCorners(graphics, upperCorner, leftCorner, lowerCorner, rightCorner);
        final int x = upperCorner + TileType.TILE_DIMENSION * GamePanel.SCALE * 2;
        final int y = leftCorner + TileType.TILE_DIMENSION * GamePanel.SCALE * 2;

        final int max = this.player.getMaxHealth();
        final int current = this.player.getHealth();
        final int shield = this.player.getShield();
        final String sword = this.player.getSwordType();
        final String color = this.player.getColor();
        final String status = this.player.getPlayerCollision();
        final String score = String.valueOf(this.player.getScore());
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
            graphics.drawImage(img, x + GamePanel.TILESIZE * i,
                y, GamePanel.TILESIZE, GamePanel.TILESIZE, null);
        }
        if ("NORMAL".equals(sword)) {
            img = this.sword;
        } else {
            img = this.greatsword;
        }
        graphics.drawImage(img, x + GamePanel.TILESIZE * i, y, GamePanel.TILESIZE,
            GamePanel.TILESIZE, null);
        Color activePowerUp;
        i++;
        if ("none".equals(color)) {
            activePowerUp = Color.GRAY;
            graphics.setColor(activePowerUp);
            graphics.fillRect(x + GamePanel.TILESIZE * i, y, GamePanel.TILESIZE, GamePanel.TILESIZE);
        } else {
            graphics.drawRect(x + GamePanel.TILESIZE * i, y, GamePanel.TILESIZE, GamePanel.TILESIZE);
            graphics.drawImage(img, x + GamePanel.TILESIZE * i,
                y, GamePanel.TILESIZE, GamePanel.TILESIZE, null);
        }
        //graphics.setColor(activePowerUp);
        //graphics.fillRect(x + GamePanel.tileSize * i, y, GamePanel.tileSize, GamePanel.tileSize);
        i++;
        if ("STUNNED".equals(status) && !"blue".equals(color)) {
            activePowerUp = Color.yellow;
        } else {
            activePowerUp = Color.lightGray;
        }
        graphics.setColor(activePowerUp);
        graphics.fillRect(x + GamePanel.TILESIZE * i, y, GamePanel.TILESIZE, GamePanel.TILESIZE);

        graphics.setColor(Color.red);
        graphics.setFont(new Font(null, Font.BOLD, GamePanel.TILESIZE));
        graphics.drawString("Score : " + score, x, rightCorner);
    }

    private void drawCorners(final Graphics2D graphics, final int upX, final int upY, final int downX,
        final int downY) {
        graphics.setColor(Color.white);
        final int thicknessCorners = TileType.TILE_DIMENSION * GamePanel.SCALE * 2;
        graphics.fillRect(upX, 0, thicknessCorners, this.gamePanel.getHeight());
        graphics.fillRect(0, upY, this.gamePanel.getWidth(), thicknessCorners);
        graphics.fillRect(downX, 0, thicknessCorners, this.gamePanel.getHeight());
        graphics.fillRect(0, downY, this.gamePanel.getWidth(), thicknessCorners);
    }

}
