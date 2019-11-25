package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        final int c = 3;
        final int[][] score = new int[c][c];
        final Game game = new Game(false, new RandomPlayer(), new RandomPlayer());

        for (int i = 0; i < c; i++) {
            for (int j = i + 1; j < c; j++) {
                System.out.println("\n\n");
                int result = game.play(new MnkBoard(8, 5, 4));
                System.out.println("Game result: " + result);

                switch (result) {
                    case 0:
                        score[i][j] = 1;
                        score[j][i] = 1;
                        break;
                    case 1:
                        score[i][j] = 3;
                        break;
                    case 2:
                        score[j][i] = 3;
                        break;
                }
            }
        }

        StringBuilder resultTable = new StringBuilder(" ");
        for (int i = 0; i < c; i++) {
            resultTable.append(" ");
            resultTable.append(i + 1);
        }
        for (int i = 0; i < c; i++) {
            resultTable.append("\n");
            resultTable.append(i + 1);
            for (int j = 0; j < c; j++) {
                resultTable.append(" ");
                resultTable.append(score[i][j]);
            }
        }
        System.out.println(resultTable.toString());
    }
}
