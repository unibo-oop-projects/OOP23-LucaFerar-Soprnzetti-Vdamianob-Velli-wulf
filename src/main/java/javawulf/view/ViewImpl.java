package javawulf.view;

import javax.swing.JFrame;
import java.awt.Dimension;

public class ViewImpl {

    // private GameLoop gameLoop;
    protected GamePanel gamePanel;

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ViewImpl() throws InterruptedException {
        this.gamePanel = new GamePanel();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // window.setResizable(false);
        window.setMinimumSize(new Dimension(GamePanel.tileSize*15, GamePanel.tileSize*15));
        window.setTitle("Java Wulf");
        window.add(this.gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
