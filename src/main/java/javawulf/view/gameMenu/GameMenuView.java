package javawulf.view.gameMenu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javawulf.view.ViewImpl;

import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenuView extends JPanel {

    public static int scale = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/400;
    public static int tileSize = 4 * scale;

    private static final int BUTTON_WIDTH = 800;
    private static final int BUTTON_HEIGHT = 120;
    private static final int BORDERS = scale * 40;

    public GameMenuView() throws InterruptedException {
        CreateMenuGUI();
    }

    private static void CreateMenuGUI() {
        JFrame frame = new JFrame("JavaWulf");
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension( 600, 480));
        frame.setLayout(new BorderLayout());

        showMenu(frame);

        frame.setVisible(true);
    }

    private static void showMenu(JFrame frame){

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
                showLeaderboard(frame);
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
    }

    private static void showLeaderboard(final JFrame frame) {
        frame.getContentPane().removeAll();

        JPanel leaderboardPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("LeaderBoard", SwingConstants.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                showMenu(frame);
                frame.setVisible(true);
            }
        });

        leaderboardPanel.add(titleLabel, BorderLayout.NORTH);
        leaderboardPanel.add(backButton, BorderLayout.SOUTH);
        
        frame.add(leaderboardPanel);
        frame.revalidate();
        frame.repaint();
    }

}
