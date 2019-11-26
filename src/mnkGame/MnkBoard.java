package mnkGame;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MnkBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.HYPHEN, '-',
            Cell.BAR, '|',
            Cell.E, '.'
            );

    private final Cell[][] cells;
    private final Position proxy;
    private int turn;
    private final int players;
    private final int k;
    private long empty;

    public MnkBoard(int m, int n, int k) {
        this(m, n, k, 2);
    }

    public MnkBoard(int m, int n, int k, int players) {
        if (m < 1 || n < 1) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }

        this.cells = new Cell[m][n];
        this.k = k;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        empty = m * n;

        turn = 0;
        proxy = new PositionProxy(this);
        this.players = players;
    }

    @Override
    public Position getPosition() {
        return proxy;
    }

    @Override
    public Cell getCell() {
        return Cell.values()[turn];
    }

    private void nextPlayer() {
        turn = (turn + 1) % players;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            nextPlayer();
            return Result.SKIP;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        empty--;

        return checkResult(move);
    }

    private Result checkResult(final Move move) {
        int r = move.getRow();
        int c = move.getColumn();
        Cell curTurn = move.getValue();

        final int[][] vectors = {
                {1, 0}, {1, 1}, {0, 1}, {-1, 1}
        };

        for (int i = 0; i < vectors.length; i++) {
            int cells = 1;
            int vx = vectors[i][0];
            int vy = vectors[i][1];
            for (int x = r + vx, y = c + vy; checkCell(x, y, curTurn); x += vx, y += vy) {
                cells++;
            }
            for (int x = r - vx, y = c - vy; checkCell(x, y, curTurn); x -= vx, y -= vy) {
                cells++;
            }
            if (cells >= k) {
                return Result.WIN;
            }
        }
        
        if (empty == 0) {
            return Result.DRAW;
        }

        nextPlayer();
        return Result.UNKNOWN;
    }

    private boolean checkCell(final int r, final int c, final Cell cell) {
        return 0 <= r && r < getM() && 0 <= c && c < getN() && cells[r][c] == cell;
    }

    @Override
    public boolean isValid(final Move move) {
        return checkCell(move.getRow(), move.getColumn(), Cell.E) && move.getValue() == getCell();
    }

    @Override
    public int getM() {
        return cells.length;
    }

    @Override
    public int getN() {
        return cells[0].length;
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < getN(); i++) {
            sb.append(" ");
            sb.append(i);
        }
        for (int r = 0; r < getM(); r++) {
            sb.append("\n");
            sb.append(r);
            sb.append(" ");
            for (int c = 0; c < getN(); c++) {
                sb.append(" ");
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
