package javawulf.view;

import javax.swing.JPanel;

import javawulf.controller.GameLoop;
import javawulf.controller.GameLoopImpl;
import javawulf.controller.PlayerStatus;
import javawulf.controller.PlayerStatusImpl;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
// import java.awt.Color;

public class GamePanel extends JPanel {
    // Screen settings
    public static final int originalTileSize = 24; // Celle da 24x24px (standard per diversi retro-game)
    public static int scale = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 400; // Fattore di scala (può essere proporzionale alla risoluzione dello schermo)
    public static int tileSize = originalTileSize * scale; // Dimensione finale effettiva delle celle (scalata)
    // Numero massimo di celle (h e w) da visualizzare in gioco
    public static final int maxScreenCol = 15;
    public static final int maxScreenRow = 15;

    private CommandListener listener;
    private GameLoop gameLoopController;
    private Drawer mapDrawer;
    private Drawer playerDrawer;
    private Drawer hudDrawer;
    private Drawer pawnDrawer;
    private Drawer itemDrawer;
    private Drawer amuletPiecesDrawer;
    private PlayerStatus playerStatus;

    public GamePanel() {
        this.gameLoopController = new GameLoopImpl(this);
        this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setBackground(java.awt.Color.WHITE);
        this.setDoubleBuffered(true);
        this.listener = new CommandListener(this.gameLoopController.getPlayerController());
        this.addKeyListener(this.listener);
        this.setFocusable(true);
        this.playerStatus = new PlayerStatusImpl(gameLoopController.getPlayer());
        this.mapDrawer = new MapDrawer(gameLoopController.getMap(), this);
        this.playerDrawer = new PlayerDrawer(this.playerStatus, this);
        this.hudDrawer = new HUDDrawer(this.playerStatus, this);
        this.pawnDrawer = new PawnDrawer(this.playerStatus, this, gameLoopController.getPawns());
        this.itemDrawer = new ItemDrawer(this, gameLoopController.getItems(), this.playerStatus);
        this.amuletPiecesDrawer = new AmuletPiecesDrawer(this, this.playerStatus, gameLoopController.getAmuletPieces());
        gameLoopController.startGameLoopThread();
    }

    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D) graphics;

        this.mapDrawer.draw(graphics2d);
        this.playerDrawer.draw(graphics2d);
        this.hudDrawer.draw(graphics2d);
        this.pawnDrawer.draw(graphics2d);
        this.itemDrawer.draw(graphics2d);
        this.amuletPiecesDrawer.draw(graphics2d);
        graphics2d.dispose();
    }
}
