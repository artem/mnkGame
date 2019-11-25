package mnkGame;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int[] pos = new int[2];
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");

            int idx = 0;
            while (idx < 2) {
                pos[idx++] = nextInt();
            }

            final Move move = new Move(pos[0], pos[1], cell);
            if (position.isValid(move)) {
                return move;
            }
            final int row = move.getRow();
            final int column = move.getColumn();
            out.println("Move " + move + " is invalid");
        }
    }

    private int nextInt() {
        while (true) {
            if (!in.hasNext()) {
                out.println("Stalemate");
                throw new IllegalStateException("Input stream has been closed");
            }
            if (in.hasNextInt()) {
                return in.nextInt();
            }
            in.next();
        }
    }
}
