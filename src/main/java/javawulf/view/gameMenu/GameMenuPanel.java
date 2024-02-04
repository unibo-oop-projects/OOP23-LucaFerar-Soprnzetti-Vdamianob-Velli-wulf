package javawulf.view.gameMenu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javawulf.view.GamePanel;
import javawulf.view.ViewImpl;

import javax.swing.Box;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenuPanel extends JPanel {

    public static int scaleX;
    public static int scaleY;
    private static int borders;

    private static final int MAX_BUTTON_WIDTH = 800;
    private static final int MAX_BUTTON_HEIGHT = 120;

    public GameMenuPanel() throws InterruptedException {
        scaleX = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2;
        scaleY = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2;
        borders = scaleX/5;
        CreateMenuGUI();
    }

    private static void CreateMenuGUI() {
        JFrame frame = new JFrame("JavaWulf");
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(scaleY, scaleX));
        frame.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        frame.setBackground(java.awt.Color.BLACK);

        showMenu(frame);

        frame.setVisible(true);
    }

    private static void showMenu(JFrame frame){

        JPanel menu = new JPanel(new GridLayout(2,2));
        JButton startButton = new JButton("PLAY");
        JButton leaderboardButton = new JButton("Leaderboard");
        JButton guideButton = new JButton("Guide");
        JButton exitButton = new JButton("Exit");

        // To limit Max button sixing
        Dimension maxButtonSize = new Dimension(MAX_BUTTON_WIDTH, MAX_BUTTON_HEIGHT);
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
                    frame.getContentPane().removeAll();
                    frame.add(new GamePanel());
                    frame.setSize(GamePanel.TILESIZE*15, GamePanel.TILESIZE*15);
                    frame.setVisible(true);
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
                JOptionPane.showMessageDialog(frame, "Press the UP key to move up\n"
                    + "Press the DOWN key to move down\n" + "Press the LEFT key to move left\n"
                    + "Press the RIGHT key to move right\n" + "Press the COMMA (,) key to attack\n");
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

        frame.add(Box.createVerticalStrut(borders), BorderLayout.NORTH);
        frame.add(Box.createVerticalStrut(borders), BorderLayout.SOUTH);
        frame.add(Box.createHorizontalStrut(borders), BorderLayout.WEST);
        frame.add(Box.createHorizontalStrut(borders), BorderLayout.EAST);
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
