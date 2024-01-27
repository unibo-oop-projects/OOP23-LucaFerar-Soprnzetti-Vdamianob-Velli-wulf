package javawulf.view;

import javax.swing.JFrame;

import javawulf.controller.GameLoop;

public class ViewImpl {

    private GamePanel gamePanel;

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ViewImpl(GameLoop loop) {
        this.gamePanel = new GamePanel(loop);

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game title");
        window.add(this.gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
