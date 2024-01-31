package javawulf.view;

import java.awt.Graphics2D;

import javawulf.model.map.Map;
import javawulf.model.map.TilePosition;
import javawulf.model.map.TileType;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class MapDrawerImpl implements MapDrawer {
    private final Map map;
    private BufferedImage imgRoom;
    private BufferedImage imgWall;
    private BufferedImage imgCorridor;
    private BufferedImage imgCentralRoom;

    public MapDrawerImpl(final Map map) {
        this.map = map;
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

        for (int x = 0; x < Map.MAP_SIZE; x++) {
            for (int y = 0; y < Map.MAP_SIZE; y++) {
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
                graphics.drawImage(img, x * TileType.TILE_DIMENSION * GamePanel.scale,
                        y * TileType.TILE_DIMENSION * GamePanel.scale, GamePanel.tileSize, GamePanel.tileSize, null);

            }
        }
    }

}
