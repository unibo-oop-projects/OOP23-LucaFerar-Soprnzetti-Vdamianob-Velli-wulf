package javawulf.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import javawulf.model.map.TileType;

public class PlayerDrawerImpl implements PlayerDrawer {

    private BufferedImage playerUp;
    private BufferedImage playerDown;
    private BufferedImage playerLeft;
    private BufferedImage playerRight;
    private BufferedImage sword;

    public PlayerDrawerImpl(){
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
    public void draw(Graphics2D graphics, int x, int y, List<Boolean> directions, boolean attack) {
        BufferedImage img = this.sword;
        if (directions.get(3)) {
            img = this.playerLeft;
        } else if (directions.get(4)) {
            img = this.playerRight;
        } else if (directions.get(1)) {
            img = this.playerUp;
        } else {
            img = this.playerDown;
        }
        graphics.drawImage(img, x * TileType.TILE_DIMENSION * GamePanel.scale,
                        y * TileType.TILE_DIMENSION * GamePanel.scale, GamePanel.tileSize, GamePanel.tileSize, null);
    }
    
}
