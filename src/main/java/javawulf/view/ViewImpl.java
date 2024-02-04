package javawulf.view;

import javax.swing.JFrame;
import java.awt.Dimension;

public final class ViewImpl {

    protected GamePanel gamePanel;

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Sets default windows parameter. it starts GamePanel
     * 
     * @throws InterruptedException
     */
    public ViewImpl() throws InterruptedException {
        this.gamePanel = new GamePanel();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // window.setResizable(false);
        window.setMinimumSize(new Dimension(GamePanel.TILESIZE * GamePanel.MAX_SCREEN_COL,
                GamePanel.TILESIZE * GamePanel.MAX_SCREEN_ROW));
        window.setTitle("Java Wulf");
        window.add(this.gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
