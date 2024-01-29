package javawulf.view;

import java.awt.Graphics2D;

import javawulf.model.map.Map;
import javawulf.model.map.TilePosition;
import javawulf.model.map.TileType;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapDrawerImpl implements MapDrawer {
    private Map map;
    BufferedImage imgRoom;
    BufferedImage imgWall;
    BufferedImage imgCorridor;

    public MapDrawerImpl(Map map) {
        this.map = map;
        try {
            this.imgRoom = ImageIO.read(getClass().getResourceAsStream(ImagePath.ROOM_TILE.getPath()));
            this.imgWall = ImageIO.read(getClass().getResourceAsStream(ImagePath.WALL_TILE.getPath()));
            this.imgCorridor = ImageIO.read(getClass().getResourceAsStream(ImagePath.CORRIDOR_TILE.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(Graphics2D graphics) {

        for (int x = 0; x < Map.MAP_SIZE; x++) {
            for (int y = 0; y < Map.MAP_SIZE; y++) {
                BufferedImage img;
                // TileType currentTile = this.gameLoop.getMap().getTilesMap().get(new
                // TilePosition(x, y));
                if (this.map.getTilesMap().containsKey(new TilePosition(x, y))) {
                    switch (this.map.getTilesMap().get(new TilePosition(x, y))) {
                        case ROOM:
                            img = imgRoom;
                            break;
                        case CORRIDOR:
                            img = imgCorridor;
                        default:
                            img = imgRoom;
                            break;
                    }
                } else {
                    img = imgWall;
                }
                graphics.drawImage(img, x * TileType.TILE_DIMENSION * GamePanel.scale,
                        y * TileType.TILE_DIMENSION * GamePanel.scale, GamePanel.tileSize, GamePanel.tileSize, null);

            }
        }
    }

}
