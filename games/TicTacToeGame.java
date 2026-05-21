package games;

public class TicTacToeGame implements Game {

    @Override
    public String getName() {
        return "Tic-Tac-Toe";
    }

    @Override
    public void start() {
        new TicTacToe();
    }
}