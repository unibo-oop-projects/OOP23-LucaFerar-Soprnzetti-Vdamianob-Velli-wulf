package javawulf.view;

import javax.swing.JPanel;

import javawulf.controller.GameLoop;
import javawulf.controller.GameLoopImpl;
import javawulf.model.map.Map;
import javawulf.model.map.TilePosition;
import javawulf.model.map.TileType;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
    // Screen settings
    final int originalTileSize = 24; // Celle da 16x16px (standard per diversi retro-game)
    final int scale = 3; // Fattore di scala (può essere proporzionale alla risoluzione dello schermo)
    public final int tileSize = originalTileSize * scale; // Dimensione finale effettiva delle celle (48x48px)
    // Numero massimo di celle (h e w) da visualizzare in gioco
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    // Dimensione (h e w) dello schermo
    final int ScreenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    private GameLoop gameLoop;

    public GamePanel() {
        this.gameLoop = new GameLoopImpl(this);
        this.setPreferredSize(new Dimension(this.ScreenWidth, this.screenHeight));
        this.setBackground(java.awt.Color.WHITE);
        this.setDoubleBuffered(true);
        // this.addKeyListener(keyHandler);
        this.setFocusable(true);

        // this.drawMap(null);this.
        gameLoop.startGameLoopThread();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D)graphics;
        // player.draw(graphics2d);

        // graphics2d.setColor(Color.red);
        // graphics2d.fillRect(100, 100, this.tileSize, this.tileSize);
        
        // BufferedImage img;
        // try {
        //     img = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
        //     i+=16;
        //     graphics2d.drawImage(img, 100+i, 100, this.tileSize, this.tileSize, null);
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        try {
            this.drawMap(graphics2d);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        graphics2d.dispose();
    }

    private void drawMap(Graphics2D graphics2d) throws IOException {
        BufferedImage imgRoom = ImageIO.read(getClass().getResourceAsStream("/tiles/room.png"));
        BufferedImage imgWall = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
        for(int x = 0; x<Map.MAP_SIZE; x++) {
            for(int y = 0; y<Map.MAP_SIZE; y++) {
                    BufferedImage img;
                    // TileType currentTile = this.gameLoop.getMap().getTilesMap().get(new TilePosition(x, y));
                    if (this.gameLoop.getMap().getTilesMap().containsKey(new TilePosition(x, y))) {
                        switch (this.gameLoop.getMap().getTilesMap().get(new TilePosition(x, y))) {
                            case ROOM:
                                img = imgRoom;
                                break;
                            default:
                                img = imgRoom;
                                break;
                        }
                    } else {
                        img = imgWall;
                    }
                    graphics2d.drawImage(img, x*TileType.TILE_DIMENSION*3, y*TileType.TILE_DIMENSION*3, this.tileSize, this.tileSize, null);
                
            }
        }
        
    }
}
