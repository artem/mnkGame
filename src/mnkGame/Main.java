package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        final boolean log = true;
        final Player[] players = {new HumanPlayer(), new HumanPlayer(), new FaultyPlayer(), new HumanPlayer()};

        final Game game = new Game(players, log);
        int result;
        do {
            System.out.println("\n");
            result = game.play(new MnkBoard(6, 6, 3, players.length));
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
}
