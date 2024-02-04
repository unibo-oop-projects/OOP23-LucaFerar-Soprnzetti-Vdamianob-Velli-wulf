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
// import java.awt.Color;

public class GamePanel extends JPanel {
    // Screen settings
    public static final int originalTileSize = 24; // Celle da 24x24px (standard per diversi retro-game)
    public static int scale = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/400; // Fattore di scala (può essere proporzionale alla risoluzione dello schermo)
    public static int tileSize = originalTileSize * scale; // Dimensione finale effettiva delle celle (scalata)
    // Numero massimo di celle (h e w) da visualizzare in gioco
    public static final int maxScreenCol = 15;
    public static final int maxScreenRow = 15;

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
