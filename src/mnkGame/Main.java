package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        final boolean log = true;
        final Player[] players = {new RandomPlayer(), new RandomPlayer(), new SequentialPlayer(), new RandomPlayer()};
        final Score score = new Score(players.length, log);
        int c = 5;

        while (c-- != 0) {
            for (int i = 0; i < players.length; i++) {
                for (int j = i + 1; j < players.length; j++) {
                    final Game game = new Game(players[i], players[j], log);
                    int result = game.play(new MnkBoard(3, 3, 3));
                    score.record(i, j, result);
                }
            }

            System.out.println("Current score table:");
            System.out.println(score);
        }

        System.out.println();
        System.out.println("Tournament is over! Results:");
        System.out.println(score);
    }
}
