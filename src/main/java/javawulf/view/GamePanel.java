package javawulf.view;

import javax.swing.JPanel;

import javawulf.controller.GameLoop;
import javawulf.controller.GameLoopImpl;
import javawulf.controller.PlayerStatus;
import javawulf.controller.PlayerStatusImpl;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * JPanel where videogame match is drawn.
 */
public class GamePanel extends JPanel {
    // Screen settings
    /** Original Tile Size (even entities and game objects have the dimension of a tile). */
    public static final int ORIGINAL_TILE_SIZE = 24;
    /** Elements scaling field. It is proportional to display resolution. */
    public static int SCALE = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/400;
    /** Scaled dimension of a tile. */
    public static int TILESIZE = ORIGINAL_TILE_SIZE * SCALE;
    // Numero massimo di celle (h e w) da visualizzare in gioco
    public static final int MAX_SCREEN_COL = 15;
    public static final int MAX_SCREEN_ROW = 15;

    private CommandListener listener;
    private GameLoop gameLoopController;
    private final List<Drawer> drawers = new ArrayList<>();
    private PlayerStatus playerStatus;

    public GamePanel() {
        this.gameLoopController = new GameLoopImpl(this);
        this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setBackground(java.awt.Color.WHITE);
        this.setDoubleBuffered(true);
        this.listener = new CommandListener(this.gameLoopController.getPlayerController());
        this.addKeyListener(this.listener);
        this.setFocusable(true);
        this.playerStatus = new PlayerStatusImpl(gameLoopController.getPlayer(), gameLoopController.getMap());
        this.drawers.add(new MapDrawer(gameLoopController.getMap(), this));
        this.drawers.add(new PlayerDrawer(this.playerStatus, this));
        this.drawers.add(new PawnDrawer(this.playerStatus, this, gameLoopController.getPawns()));
        this.drawers.add(new ItemDrawer(this, gameLoopController.getItems(), this.playerStatus));
        this.drawers.add(new AmuletPiecesDrawer(this, this.playerStatus, gameLoopController.getAmuletPieces()));
        this.drawers.add(new PowerUpsDrawer(this, gameLoopController.getPowerUps(), this.playerStatus));
        this.drawers.add(new HUDDrawer(this.playerStatus, this));
        gameLoopController.startGameLoopThread();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D)graphics;

        for (Drawer drawer : drawers) {
            drawer.draw(graphics2d);
        }
        graphics2d.dispose();
    }
}
