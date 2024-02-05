package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javawulf.controller.PlayerStatus;
import javawulf.model.item.AmuletPiece;
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
    private final List<AmuletPiece> pieces;

    /**
     * Creates a new HUDDrawer.
     * 
     * @param player The player character whose status will be made into the HUD
     * @param pieces The amulet piece that still have to be collected
     * @param gamePanel The panel where the HUD must appear in.
     */
    @SuppressFBWarnings(
        value = {
            "M", "V", "EI2"
        },
        justification = "Game panel is stored to allow calculations for the drawings that are relative "
            + "to its size. The pieces are passed in order to check whether the Player's"
            + " position relative to the pieces"
    )
    public HUDDrawer(final PlayerStatus player, final List<AmuletPiece> pieces, final GamePanel gamePanel) {
        super(gamePanel, player);
        this.gamePanel = gamePanel;
        this.player = player;
        this.pieces = pieces;
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

        this.drawHud(graphics, x, y);

        final int score = this.player.getScore();
        this.drawScore(graphics, score, x, rightCorner);
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

    private void drawHud(final Graphics2D graphics, final int x, final int y) {
        final int maxHealth = this.player.getMaxHealth();
        final int current = this.player.getHealth();
        final int shield = this.player.getShield();
        final String sword = this.player.getSwordType();
        final String color = this.player.getColor();
        final String status = this.player.getPlayerCollision();
        BufferedImage img;
        int i;
        for (i = 0; i < maxHealth + shield; i++) {
            if (maxHealth <= i) {
                img = this.shield;
            } else if (current > i) {
                img = this.health;
            } else { 
                img = this.maxHealth;
            }
            graphics.drawImage(img, x + GamePanel.TILESIZE * i,
                y, GamePanel.TILESIZE, GamePanel.TILESIZE, null);
        }
        img = "NORMAL".equals(sword) ? this.sword : this.greatsword;
        graphics.drawImage(img, x + GamePanel.TILESIZE * i, y, GamePanel.TILESIZE,
            GamePanel.TILESIZE, null);
        i++;
        final Color activePowerUp = this.colorPowerUpActive(color);
        graphics.setColor(activePowerUp);
        graphics.fillRect(x + GamePanel.TILESIZE * i, y, GamePanel.TILESIZE, GamePanel.TILESIZE);
        i++;
        final Color stunStatus = "STUNNED".equals(status) && !"blue".equals(color) ? Color.ORANGE : Color.lightGray;
        graphics.setColor(stunStatus);
        graphics.fillRect(x + GamePanel.TILESIZE * i, y, GamePanel.TILESIZE, GamePanel.TILESIZE);
        i++;
        graphics.setColor(this.isPlayerAligned() ? Color.MAGENTA : Color.BLACK);
        graphics.fillRect(x + GamePanel.TILESIZE * i, y, GamePanel.TILESIZE, GamePanel.TILESIZE);
    }

    private boolean isPlayerAligned() {
        for (final AmuletPiece piece : this.pieces) {
            if (piece.getPosition().getX() > (player.getPlayerX() - GamePanel.ORIGINAL_TILE_SIZE)
                && piece.getPosition().getX() < (player.getPlayerX() + GamePanel.ORIGINAL_TILE_SIZE)
                || piece.getPosition().getY() > (player.getPlayerY() - GamePanel.ORIGINAL_TILE_SIZE)
                && piece.getPosition().getY() < (player.getPlayerY() + GamePanel.ORIGINAL_TILE_SIZE)) {
                return true;
            }
        }
        return false;
    }

    private void drawScore(final Graphics2D graphics, final int score, final int x, final int y) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font(null, Font.BOLD, GamePanel.TILESIZE));
        graphics.drawString("Score : " + score, x, y);
    }

    private Color colorPowerUpActive(final String color) {
        final Color activePowerUp;
        switch (color) {
            case "red":
                activePowerUp = Color.RED;
                break;
            case "blue":
                activePowerUp = Color.BLUE;
                break;
            case "yellow":
                activePowerUp   = Color.YELLOW;
                break;
            case "green":
                activePowerUp = Color.GREEN;
                break;
            default:
                activePowerUp = Color.GRAY;
                break;
        }
        return activePowerUp;
    }

}
