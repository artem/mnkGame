package mnkGame;

public class FaultyPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
        return new Move(-1, -1, Cell.E);
    }
}
