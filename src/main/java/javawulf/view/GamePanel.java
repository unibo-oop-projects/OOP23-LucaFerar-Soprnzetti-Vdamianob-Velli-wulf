package javawulf.view;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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

    public GamePanel() {

        this.setPreferredSize(new Dimension(this.ScreenWidth, this.screenHeight));
        this.setBackground(java.awt.Color.WHITE);
        this.setDoubleBuffered(true);
        // this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D)graphics;
        // player.draw(graphics2d);
        graphics2d.dispose();
    }
}
