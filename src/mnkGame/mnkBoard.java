package mnkGame;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class mnkBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private final Position proxy;
    private Cell turn;
    private final int k;
    private long empty;

    public mnkBoard(int m, int n, int k) {
        if (m < 1 || n < 1) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }

        this.cells = new Cell[m][n];
        this.k = k;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        empty = m * n;

        turn = Cell.X;
        proxy = new PositionProxy(this);
    }

    @Override
    public Position getPosition() {
        return proxy;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        empty--;

        return checkResult(move);
    }

    private Result checkResult(final Move move) {
        int r = move.getRow();
        int c = move.getColumn();
        Cell curTurn = move.getValue();

        int inV = 1;
        int inH = 1;
        int inDiag1 = 1;
        int inDiag2 = 1;
        boolean skipL = false;
        boolean skipTL = false;
        boolean skipT = false;
        boolean skipTR = false;
        boolean skipR = false;
        boolean skipBR = false;
        boolean skipB = false;
        boolean skipBL = false;

        for (int i = 1; i < k; i++) {
            // Same row -
            if (!skipL && checkCell(r, c - i, curTurn)) {
                inH++;
            } else {
                skipL = true;
            }
            if (!skipR && checkCell(r, c + i, curTurn)) {
                inH++;
            } else {
                skipR = true;
            }

            // Same diagonal (\)
            if (!skipTL && checkCell(r - i, c - i, curTurn)) {
                inDiag1++;
            } else {
                skipTL = true;
            }
            if (!skipBR && checkCell(r + i, c + i, curTurn)) {
                inDiag1++;
            } else {
                skipBR = true;
            }

            // Same column (|)
            if (!skipT &&checkCell(r, c - i, curTurn)) {
                inV++;
            } else {
                skipT = true;
            }
            if (!skipB && checkCell(r, c + i, curTurn)) {
                inV++;
            } else {
                skipB = true;
            }

            // Same back diagonal (/)
            if (!skipTR && checkCell(r - i, c + i, curTurn)) {
                inDiag2++;
            } else {
                skipTR = true;
            }
            if (!skipBL && checkCell(r + i, c - i, curTurn)) {
                inDiag2++;
            } else {
                skipBL = true;
            }
        }

        if (inV >= k || inH >= k || inDiag1 >= k || inDiag2 >= k) {
            return Result.WIN;
        }
        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
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
