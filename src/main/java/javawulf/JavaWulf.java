package javawulf;

import javax.swing.SwingUtilities;

import javawulf.view.gamemenu.GameMenuPanel;

public class JavaWulf {
    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(() -> {
            try {
                new GameMenuPanel();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //new ViewImpl();
        // GameLoop loop = new GameLoopImpl();
        // loop.startGameLoopThread();
    }
}
