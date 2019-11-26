package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        final boolean log = true;
        final Player[] players = {new RandomPlayer(), new RandomPlayer(), new SequentialPlayer(), new RandomPlayer()};

        final Game game = new Game(players, log);
        int result;
        do {
            System.out.println("\n");
            result = game.play(new MnkBoard(3, 3, 3, players.length));
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
}
