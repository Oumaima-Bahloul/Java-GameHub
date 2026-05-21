package games;

public class BlackJackGame implements Game {

    @Override
    public String getName() {
        return "Black Jack";
    }

    @Override
    public void start() {
        new BlackJack();
    }
}