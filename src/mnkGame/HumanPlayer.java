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
                if (in.hasNext()) {
                    if (in.hasNextInt()) {
                        pos[idx++] = in.nextInt();
                    } else {
                        in.next();
                    }
                }/* else {
                    out.println("Player gave up!");
                    in.next();
                    return new Move(-1, -1, Cell.E);
                }*/
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
}
