package mnkGame;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Position {
    int getM();
    int getN();
    boolean isValid(Move move);

    Cell getCell(int r, int c);
}
