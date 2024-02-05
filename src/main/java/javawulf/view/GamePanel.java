package javawulf.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javawulf.controller.GameLoop;
import javawulf.controller.GameLoopImpl;
import javawulf.controller.PlayerStatus;
import javawulf.controller.PlayerStatusImpl;
import javawulf.view.gamemenu.GameMenuPanel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * JPanel where videogame match is drawn.
 */
public final class GamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    // Screen settings
    /** Original Tile Size (even entities and game objects have the dimension of a tile). */
    public static final int ORIGINAL_TILE_SIZE = 24;
    /** Constant used to calculate SCALE.  */
    private static final int SCALE_FACTOR = 400;
    /** Elements scaling field. It is proportional to display resolution. */
    public static final int SCALE = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / SCALE_FACTOR;
    /** Scaled dimension of a tile. */
    public static final int TILESIZE = ORIGINAL_TILE_SIZE * SCALE;
    /** Max columns of tile (width). */
    public static final int MAX_SCREEN_COL = 15;
    /** Max rows of tile (height). */
    public static final int MAX_SCREEN_ROW = 15;

    private final CommandListener listener;
    private final GameLoop gameLoopController;
    private final List<Drawer> drawers = new ArrayList<>();
    private final PlayerStatus playerStatus;
    private final JFrame frame;

    /**
     * Used to inizialize GamePanel. 
     * It starts an importart part of in-gaming controller: Game Loop.
     * It sets several default view details, like drawers of components.
     * @param frame 
     */
    public GamePanel(final JFrame frame) {
        this.frame = frame;
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
        this.drawers.add(new HUDDrawer(this.playerStatus, this.gameLoopController.getAmuletPieces(), this));
        gameLoopController.startGameLoopThread();
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D graphics2d = (Graphics2D) graphics;

        for (Drawer drawer : drawers) {
            drawer.draw(graphics2d);
        }
        graphics2d.dispose();
    }

    /**
     * 
     * @param gameWon flag who knows if player is winner
     * @param score
     */
    public void resetFrame(final boolean gameWon, final int score) {
        String value = gameWon ? "CONGRATULATIONS! You escaped sucessfully" : "Oh no, Game Over. Better luck next time!";
        JOptionPane.showMessageDialog(this, value + "\n Your point total is " + score);
        this.setVisible(false);
        // Save scoreBoard
        // Clear the frame
        frame.getContentPane().removeAll();
        // Open gameMenuPanel
        GameMenuPanel.createMenuGUI(frame);
    }
}
