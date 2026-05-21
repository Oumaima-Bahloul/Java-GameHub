package games;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RockPaperScissor extends JFrame implements ActionListener {
    ImageIcon rock;
    ImageIcon paper;
    ImageIcon scissors;
    JButton rockButton, paperButton, scissorButton;
    JLabel computerChoice;
    JLabel computerScoreLabel, playerScoreLabel;

    private static final String[] computerChoices = {"Rock", "Paper", "Scissors"};
    private String compChoice;
    private int computerScore, playerScore;
    private Random random = new Random();

    public RockPaperScissor() {
        super("Rock Paper Scissors");
        setSize(450, 574);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#0d0d0d"));
        addGuiComponents();
        rock = new ImageIcon(new ImageIcon(getClass().getResource("/rockpaperscissors/rock.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        paper = new ImageIcon(new ImageIcon(getClass().getResource("/rockpaperscissors/paper.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        scissors = new ImageIcon(new ImageIcon(getClass().getResource("/rockpaperscissors/scissors.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));

        rockButton.setIcon(rock);
        paperButton.setIcon(paper);
        scissorButton.setIcon(scissors);
        rockButton.setText("");
        paperButton.setText("");
        scissorButton.setText("");
        setVisible(true);
    }

    private String play(String playerChoice) {
        compChoice = computerChoices[random.nextInt(3)];
        String result;
        if (compChoice.equals(playerChoice)) {
            result = "Draw";
        } else if ((playerChoice.equals("Rock") && compChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && compChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && compChoice.equals("Paper"))) {
            result = "Player Wins";
            playerScore++;
        } else {
            result = "Computer Wins";
            computerScore++;
        }
        return result;
    }

    private void addGuiComponents() {
        computerScoreLabel = new JLabel("Computer: 0");
        computerScoreLabel.setBounds(0, 43, 450, 30);
        computerScoreLabel.setFont(new Font("Dialog", Font.BOLD, 26));
        computerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        computerScoreLabel.setForeground(Color.decode("#00ff88"));
        add(computerScoreLabel);

        computerChoice = new JLabel("?");
        computerChoice.setBounds(175, 118, 98, 81);
        computerChoice.setFont(new Font("Dialog", Font.PLAIN, 18));
        computerChoice.setHorizontalAlignment(SwingConstants.CENTER);
        computerChoice.setForeground(Color.decode("#00ff88"));
        computerChoice.setBorder(BorderFactory.createLineBorder(Color.decode("#00ff88"), 2));
        add(computerChoice);

        playerScoreLabel = new JLabel("Player: 0");
        playerScoreLabel.setBounds(0, 317, 450, 30);
        playerScoreLabel.setFont(new Font("Dialog", Font.BOLD, 26));
        playerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerScoreLabel.setForeground(Color.decode("#00ff88"));
        add(playerScoreLabel);

        rockButton = new JButton("Rock");
        rockButton.setBounds(40, 387, 105, 81);
        rockButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        rockButton.setBackground(Color.decode("#111111"));
        rockButton.setForeground(Color.decode("#00ff88"));
        rockButton.setBorder(BorderFactory.createLineBorder(Color.decode("#00ff88"), 2));
        rockButton.addActionListener(this);
        add(rockButton);

        paperButton = new JButton("Paper");
        paperButton.setBounds(165, 387, 105, 81);
        paperButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        paperButton.setBackground(Color.decode("#111111"));
        paperButton.setForeground(Color.decode("#00ff88"));
        paperButton.setBorder(BorderFactory.createLineBorder(Color.decode("#00ff88"), 2));
        paperButton.addActionListener(this);
        add(paperButton);

        scissorButton = new JButton("Scissors");
        scissorButton.setBounds(290, 387, 105, 81);
        scissorButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        scissorButton.setBackground(Color.decode("#111111"));
        scissorButton.setForeground(Color.decode("#00ff88"));
        scissorButton.setBorder(BorderFactory.createLineBorder(Color.decode("#00ff88"), 2));
        scissorButton.addActionListener(this);
        add(scissorButton);
    }

    private void showDialog(String message) {
        JDialog resultDialog = new JDialog(this, "Result", true);
        resultDialog.setSize(227, 124);
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.setResizable(false);

        JLabel resultLabel = new JLabel(message);
        resultLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultDialog.add(resultLabel, BorderLayout.CENTER);

        JButton tryAgainButton = new JButton("Try Again?");
        tryAgainButton.addActionListener(e -> {
            computerChoice.setIcon(null);
            computerChoice.setText("?");
            resultDialog.dispose();
        });
        resultDialog.add(tryAgainButton, BorderLayout.SOUTH);
        resultDialog.setLocationRelativeTo(this);
        resultDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerChoice = e.getActionCommand();
        String result = play(playerChoice);

        computerChoice.setText("");
        if (compChoice.equals("Rock")) computerChoice.setIcon(rock);
        else if (compChoice.equals("Paper")) computerChoice.setIcon(paper);
        else computerChoice.setIcon(scissors);

        computerScoreLabel.setText("Computer: " + computerScore);
        playerScoreLabel.setText("Player: " + playerScore);
        showDialog(result);
    }
}