package mnkGame;

public class Score {
    final int[][] score;

    public Score(int c) {
        score = new int[c][c];
    }

    public int record(int i, int j, int result) {
        int winner = 0;
        switch (result) {
            case 0:
                score[i][j] = 1;
                score[j][i] = 1;
                winner = 0;
                break;
            case 1:
                score[i][j] = 3;
                winner = i + 1;
                break;
            case 2:
                score[j][i] = 3;
                winner = j + 1;
                break;
        }
        return winner;
    }

    @Override
    public String toString() {
        StringBuilder resultTable = new StringBuilder(" ");
        for (int i = 0; i < score.length; i++) {
            resultTable.append(" ");
            resultTable.append(i + 1);
        }
        for (int i = 0; i < score.length; i++) {
            resultTable.append("\n");
            resultTable.append(i + 1);
            for (int j = 0; j < score.length; j++) {
                resultTable.append(" ");
                resultTable.append(score[i][j]);
            }
        }

        return resultTable.toString();
    }
}
