import games.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Game> games = new ArrayList<>();
        games.add(new PacManGame());
        games.add(new FlappyBirdGame());
        games.add(new SudokuGame());
        games.add(new SnakeGameGame());
        games.add(new TicTacToeGame());
        games.add(new BlackJackGame());
        games.add(new RockPaperScissorGame());

        JFrame frame = new JFrame("Game Hub");
        frame.setSize(400, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#0d0d0d"));
        mainPanel.setLayout(new BorderLayout());

        // title
        JLabel title = new JLabel("GAME HUB", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.decode("#00ff88"));
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        mainPanel.add(title, BorderLayout.NORTH);

        // buttons panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#0d0d0d"));
        panel.setLayout(new GridLayout(games.size(), 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 60, 60, 60));

        for (Game game : games) {
            JButton button = new JButton(game.getName());
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setForeground(Color.decode("#00ff88"));
            button.setBackground(Color.decode("#111111"));
            button.setBorder(BorderFactory.createLineBorder(Color.decode("#00ff88"), 2));
            button.setFocusable(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // hover effect
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    button.setBackground(Color.decode("#00ff88"));
                    button.setForeground(Color.decode("#000000"));
                }
                public void mouseExited(java.awt.event.MouseEvent e) {
                    button.setBackground(Color.decode("#111111"));
                    button.setForeground(Color.decode("#00ff88"));
                }
            });

            button.addActionListener(e -> game.start());
            panel.add(button);
        }

        mainPanel.add(panel, BorderLayout.CENTER);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}