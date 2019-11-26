package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private final Player[] players;

    public Game(final Player[] players, final boolean log) {
        if (players.length > Cell.values().length - 1) {
            throw new IllegalArgumentException("Too much players were given");
        }
        this.players = players;
        this.log = log;
    }

    public int play(Board board) {
        for (int i = 0; ; i = (i + 1) % players.length) {
            final int result = move(board, players[i], i + 1);
            if (result != -1) {
                log();
                log("Game result: " + result);
                return result;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose"); // TODO FIXME
            return Cell.values().length - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }

    private void log() {
        if (log) {
            System.out.println();
        }
    }
}
