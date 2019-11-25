package mnkGame;

public class PositionProxy implements Position {
    private final Position board;

    public PositionProxy(Position board) {
        this.board = board;
    }

    @Override
    public int getM() {
        return board.getM();
    }

    @Override
    public int getN() {
        return board.getN();
    }

    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return board.getCell(r, c);
    }
}
