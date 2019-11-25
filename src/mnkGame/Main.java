package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        final Player[] players = {new RandomPlayer(), new RandomPlayer(), new SequentialPlayer(), new RandomPlayer()};
        final int c = players.length;
        final Score score = new Score(c);

        for (int i = 0; i < c; i++) {
            for (int j = i + 1; j < c; j++) {
                final Game game = new Game(false, players[i], players[j]);
                int result = game.play(new MnkBoard(3, 3, 3));
                int winner = score.record(i, j, result);

                System.out.println();
                System.out.println("Players: " + (i + 1) + " and " + (j + 1));
                System.out.println("Game result: " + winner);
                System.out.println("Current score table:");
                System.out.println(score);
            }
        }
        System.out.println();
        System.out.println("Tournament is over! Results:");
        System.out.println(score.toString());
    }
}
