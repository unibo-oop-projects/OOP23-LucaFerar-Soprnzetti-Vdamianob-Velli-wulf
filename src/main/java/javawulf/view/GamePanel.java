package javawulf.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javafx.scene.paint.Color;

public class GamePanel extends JPanel {
    // Screen settings
    final int originalTileSize = 16; // Celle da 16x16px (standard per diversi retro-game)
    final int scale = 3; // Fattore di scala
    public final int tileSize = originalTileSize * scale; // Dimensione finale effettiva delle celle (48x48px)
    // Numero massimo di celle (h e w) da visualizzare in gioco
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    // Dimensione (h e w) dello schermo
    final int ScreenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public GamePanel() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game title");
        // GamePanel gamePanel = new GamePanel();
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        this.setPreferredSize(new Dimension(this.ScreenWidth, this.screenHeight));
        this.setBackground(java.awt.Color.BLACK);
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
