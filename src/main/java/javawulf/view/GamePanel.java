package javawulf.view;

import javax.swing.JPanel;

import javawulf.controller.GameLoop;
import javawulf.controller.GameLoopImpl;
import javawulf.controller.PlayerStatus;
import javawulf.controller.PlayerStatusImpl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
// import java.awt.Color;

public class GamePanel extends JPanel {
    // Screen settings
    public static final int originalTileSize = 24; // Celle da 16x16px (standard per diversi retro-game)
    public static final int scale = 2; // Fattore di scala (può essere proporzionale alla risoluzione dello schermo)
    public static final int tileSize = originalTileSize * scale; // Dimensione finale effettiva delle celle (48x48px)
    // Numero massimo di celle (h e w) da visualizzare in gioco
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    // Dimensione (h e w) dello schermo
    private int screenWidth = tileSize * maxScreenCol;
    private int screenHeight = tileSize * maxScreenRow;

    private CommandListener listener;
    private GameLoop gameLoopController;
    private Drawer mapDrawer;
    private Drawer playerDrawer;
    private Drawer hudDrawer;
    private PlayerStatus playerStatus;

    public GamePanel() {
        this.gameLoopController = new GameLoopImpl(this);
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(java.awt.Color.WHITE);
        this.setDoubleBuffered(true);
        this.listener = new CommandListener(this.gameLoopController.getPlayerController());
        this.addKeyListener(this.listener);
        this.setFocusable(true);
        this.playerStatus = new PlayerStatusImpl(gameLoopController.getPlayer());
        this.mapDrawer = new MapDrawer(gameLoopController.getMap(), this);
        this.playerDrawer = new PlayerDrawer(this.playerStatus, this);
        this.hudDrawer = new HUDDrawer(this.playerStatus, this);
        gameLoopController.startGameLoopThread();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D)graphics;

        this.mapDrawer.draw(graphics2d);
        this.playerDrawer.draw(graphics2d);
        this.hudDrawer.draw(graphics2d);
        graphics2d.dispose();
    }
}
