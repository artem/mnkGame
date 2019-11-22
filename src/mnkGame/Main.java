package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
        int result;
        do {
            System.out.println("\n\n\n");
            result = game.play(new mnkBoard(8, 5, 4));
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
}
