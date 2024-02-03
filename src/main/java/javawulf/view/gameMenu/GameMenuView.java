package javawulf.view.gameMenu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

public class GameMenuView extends JPanel {

    public GameMenuView() throws InterruptedException {
        CreateMenuGUI();
    }

    private static void CreateMenuGUI() {
        JFrame frame = new JFrame("JavaWulf");
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension( 600, 480));
        frame.setLayout(new GridLayout(4,1));

        JButton startButton = new JButton("PLAY");
        JButton leaderboardButton = new JButton("Leaderboard");
        JButton guideButton = new JButton("Guide");
        JButton exitButton = new JButton("Exit");


        frame.add(startButton);
        frame.add(leaderboardButton);
        frame.add(guideButton);
        frame.add(exitButton);

        frame.setVisible(true);
    }

}
