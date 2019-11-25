package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        final int c = 3;
        final Score score = new Score(c);
        final Game game = new Game(false, new RandomPlayer(), new RandomPlayer());

        for (int i = 0; i < c; i++) {
            for (int j = i + 1; j < c; j++) {
                System.out.println("\n");
                int result = game.play(new MnkBoard(3, 3, 3));
                int winner = score.record(i, j, result);

                System.out.println("Game result: " + winner);
                System.out.println("Current score table:");
                System.out.println(score);
            }
        }
        System.out.println("Tournament is over! Results:");
        System.out.println(score.toString());
    }
}
