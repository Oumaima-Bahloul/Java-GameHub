package games;

import javax.swing.JFrame;//wing for the game

public class PacManGame implements Game {

    @Override
    public String getName() {
        return "Pac-Man";
    }
    //my game frame size
    @Override
    public void start() {
        int rowCount = 21;
        int columnCount = 19;
        int tileSize = 32;
        int boardWidth = columnCount * tileSize;
        int boardHeight = rowCount * tileSize;

        JFrame frame = new JFrame("Pac Man");
        //frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight); //to set the size
        frame.setLocationRelativeTo(null);  // set the window to the center of the game
        frame.setResizable(false); // so the player doesnt resize the game window
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // to close the game when pressing the X button
        PacMan pacmanGame = new PacMan();
        frame.add(pacmanGame);
        frame.pack(); //to get the full size of the JPanel within the window
        frame.setVisible(true);
        pacmanGame.requestFocus();

    }
}


