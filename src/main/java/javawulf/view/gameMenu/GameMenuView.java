package javawulf.view.gameMenu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javawulf.view.ViewImpl;

import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenuView extends JPanel {

    private static final int BUTTON_WIDTH = 800;
    private static final int BUTTON_HEIGHT = 120;
    private static final int BORDERS = 100;

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
        Dimension maxButtonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        startButton.setMaximumSize(maxButtonSize);
        leaderboardButton.setMaximumSize(maxButtonSize);
        guideButton.setMaximumSize(maxButtonSize);
        exitButton.setMaximumSize(maxButtonSize);

        // Start Game
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    new ViewImpl();
                } catch (final Exception exceptionViewImpl) {
                    exceptionViewImpl.printStackTrace();
                }
            }
        });
        // Show LeaderBoard
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "needToCreateLeaderBoard");
            }
        });
        // Show Guide
        guideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "needToCreateAguide");
            }
        });
        // Exit 
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
