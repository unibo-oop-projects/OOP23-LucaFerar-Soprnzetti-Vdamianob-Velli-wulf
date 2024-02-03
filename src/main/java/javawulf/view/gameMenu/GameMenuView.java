package javawulf.view.gameMenu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameMenuView extends JPanel {

    private static int BORDERS = 100;

    public GameMenuView() throws InterruptedException {
        CreateMenuGUI();
    }

    private static void CreateMenuGUI() {
        JFrame frame = new JFrame("JavaWulf");
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension( 600, 480));
        frame.setLayout(new BorderLayout());

        JPanel menu = new JPanel(new GridLayout(4,1));
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        JButton startButton = new JButton("PLAY");
        JButton leaderboardButton = new JButton("Leaderboard");
        JButton guideButton = new JButton("Guide");
        JButton exitButton = new JButton("Exit");

        // To limit Max button sixing
        Dimension maxButtonSize = new Dimension(600, 100);
        startButton.setMaximumSize(maxButtonSize);
        leaderboardButton.setMaximumSize(maxButtonSize);
        guideButton.setMaximumSize(maxButtonSize);
        exitButton.setMaximumSize(maxButtonSize);

        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(frame,
                        "Sure you want to quit?",
                        "Confirm exit",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        menu.add(startButton);
        menu.add(leaderboardButton);
        menu.add(guideButton);
        menu.add(exitButton);

        frame.add(menu, BorderLayout.CENTER);

        frame.add(Box.createVerticalStrut(BORDERS), BorderLayout.NORTH);
        frame.add(Box.createVerticalStrut(BORDERS), BorderLayout.SOUTH);
        frame.add(Box.createHorizontalStrut(BORDERS), BorderLayout.WEST);
        frame.add(Box.createHorizontalStrut(BORDERS), BorderLayout.EAST);

    

        frame.setVisible(true);
    }

}
