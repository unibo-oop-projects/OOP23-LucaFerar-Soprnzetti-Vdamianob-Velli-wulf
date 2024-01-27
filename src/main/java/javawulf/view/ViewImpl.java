package javawulf.view;

import javax.swing.JFrame;

import javawulf.controller.GameLoop;
import javawulf.controller.GameLoopImpl;

public class ViewImpl {

    // private GameLoop gameLoop;
    private GamePanel gamePanel;

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ViewImpl() throws InterruptedException {
        this.gamePanel = new GamePanel();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // window.setResizable(false);
        window.setTitle("Java Wulf");
        window.add(this.gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
