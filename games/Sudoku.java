package games;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class Sudoku {
    class Tile extends JButton {
        int r;
        int c;
        Tile(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    int boardWidth = 600;
    int boardHeight = 650;

    //give random games and solutions
    String[] puzzle;
    String[] solution;
    Random random = new Random();

    JFrame frame = new JFrame("Sudoku");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    JButton numSelected = null;
    int errors = 0;

    Sudoku() {
        generateSudoku();
        // frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.BOLD, 30));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Sudoku: 0");

        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(9, 9));
        setupTiles();
        frame.add(boardPanel, BorderLayout.CENTER);

        buttonsPanel.setLayout(new GridLayout(1, 9));
        setupButtons();
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    void setupTiles() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Tile tile = new Tile(r, c);
                char tileChar = puzzle[r].charAt(c);
                if (tileChar != '-') {
                    tile.setFont(new Font("Arial", Font.BOLD, 20));
                    tile.setText(String.valueOf(tileChar));
                    tile.setBackground(Color.lightGray);
                }
                else {
                    tile.setFont(new Font("Arial", Font.PLAIN, 20));
                    tile.setBackground(Color.white);
                }
                if ((r == 2 && c == 2) || (r == 2 && c == 5) || (r == 5 && c == 2) || (r == 5 && c == 5)) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 5, Color.black));
                }
                else if (r == 2 || r == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.black));
                }
                else if (c == 2 || c == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 5, Color.black));
                }
                else {
                    tile.setBorder(BorderFactory.createLineBorder(Color.black));
                }
                tile.setFocusable(false);
                boardPanel.add(tile);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Tile tile = (Tile) e.getSource();
                        int r = tile.r;
                        int c = tile.c;
                        if (numSelected != null) {
                            if (tile.getText() != "") {
                                return;
                            }
                            String numSelectedText = numSelected.getText();
                            String tileSolution = String.valueOf(solution[r].charAt(c));
                            if (tileSolution.equals(numSelectedText)) {
                                tile.setText(numSelectedText);
                            }
                            else {
                                errors += 1;
                                textLabel.setText("Sudoku: " + String.valueOf(errors));
                            }

                        }
                    }
                });
            }
        }
    }

    void setupButtons() {
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton();
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setText(String.valueOf(i));
            button.setFocusable(false);
            button.setBackground(Color.white);
            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    if (numSelected != null) {
                        numSelected.setBackground(Color.white);
                    }
                    numSelected = button;
                    numSelected.setBackground(Color.lightGray);
                }
            });
        }
    }
    void generateSudoku() {
        int[][] board = new int[9][9];
        fillBoard(board);

        solution = new String[9];
        for (int r = 0; r < 9; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < 9; c++) sb.append(board[r][c]);
            solution[r] = sb.toString();
        }

        removeNumbers(board);

        puzzle = new String[9];
        for (int r = 0; r < 9; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < 9; c++) sb.append(board[r][c] == 0 ? "-" : board[r][c]);
            puzzle[r] = sb.toString();
        }
    }

    boolean fillBoard(int[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == 0) {
                    int[] nums = shuffledNumbers();
                    for (int num : nums) {
                        if (isValid(board, r, c, num)) {
                            board[r][c] = num;
                            if (fillBoard(board)) return true;
                            board[r][c] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    boolean isValid(int[][] board, int r, int c, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == num) return false;
            if (board[i][c] == num) return false;
            if (board[3*(r/3) + i/3][3*(c/3) + i%3] == num) return false;
        }
        return true;
    }

    void removeNumbers(int[][] board) {
        int toRemove = 40;
        while (toRemove > 0) {
            int r = random.nextInt(9);
            int c = random.nextInt(9);
            if (board[r][c] != 0) {
                board[r][c] = 0;
                toRemove--;
            }
        }
    }

    int[] shuffledNumbers() {
        int[] nums = {1,2,3,4,5,6,7,8,9};
        for (int i = 8; i > 0; i--) {
            int j = random.nextInt(i+1);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }
}
