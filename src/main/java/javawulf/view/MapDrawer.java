package javawulf.view;

import java.awt.Graphics2D;

import javawulf.model.map.Map;
import javawulf.model.map.TilePosition;
import javawulf.model.map.TileType;
import javawulf.model.player.Player;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Used in GamePanel for drawing Map (Game World).
 */
public final class MapDrawer implements Drawer {
    private final Map map;
    private BufferedImage imgRoom;
    private BufferedImage imgWall;
    private BufferedImage imgCorridor;
    private BufferedImage imgCentralRoom;
    private GamePanel gamePanel;

    public MapDrawer(final Map map, GamePanel gamePanel) {
        this.map = map;
        this.gamePanel = gamePanel;
        try {
            this.imgRoom = ImageIO.read(getClass().getResourceAsStream(ImagePath.ROOM_TILE.getPath()));
            this.imgWall = ImageIO.read(getClass().getResourceAsStream(ImagePath.WALL_TILE.getPath()));
            this.imgCorridor = ImageIO.read(getClass().getResourceAsStream(ImagePath.CORRIDOR_TILE.getPath()));
            this.imgCentralRoom = ImageIO.read(getClass().getResourceAsStream(ImagePath.CENTRAL_ROOM_TILE.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(final Graphics2D graphics) {
        TilePosition playerPos = this.map.getTilePosition(this.map.getPlayer().getPosition()).get();
        for (int x = playerPos.getX() - 8; x < playerPos.getX() + 8; x++) {
            for (int y = playerPos.getY() - 7; y < playerPos.getY() + 8; y++) {
                BufferedImage img;
                if (this.map.getTilesMap().containsKey(new TilePosition(x, y))) {
                    switch (this.map.getTilesMap().get(new TilePosition(x, y))) {
                        case ROOM:
                            img = imgRoom;
                            break;
                        case CENTRAL_ROOM:
                            img = imgCentralRoom;
                            break;
                        case CORRIDOR:
                            img = imgCorridor;
                            break;
                        default:
                            img = imgRoom;
                            break;
                    }
                } else {
                    img = imgWall;
                }
                graphics.drawImage(img, x * GamePanel.tileSize + (this.gamePanel.getWidth()/2 - Player.OBJECT_SIZE/2) - (int) map.getPlayer().getBounds().getCollisionArea().getX() * GamePanel.scale,
                        y * GamePanel.tileSize + (this.gamePanel.getHeight()/2 - Player.OBJECT_SIZE/2) - (int) map.getPlayer().getBounds().getCollisionArea().getY() * GamePanel.scale, GamePanel.tileSize, GamePanel.tileSize, null);
            }
        }
    }

}
