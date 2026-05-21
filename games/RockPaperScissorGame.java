package games;

public class RockPaperScissorGame implements Game {

    @Override
    public String getName() {
        return "Rock Paper Scissors";
    }

    @Override
    public void start() {
        new RockPaperScissor();
    }
}