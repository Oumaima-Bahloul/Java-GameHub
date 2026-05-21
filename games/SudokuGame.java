package games;

public class SudokuGame implements Game {

    @Override
    public String getName() {
        return "Sudoku";
    }

    @Override
    public void start() {
        new Thread(() -> {
            try {
                new Sudoku();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}