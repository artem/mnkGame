package mnkGame;

public class Score {
    private final int[][] score;
    private final boolean log;

    public Score(final int c, final boolean log) {
        score = new int[c][c];
        this.log = log;
    }

    public void record(final int i, final int j, final int result) {
        log("Players " + (i+1) + " and " + (j+1) + ", result: " + result);
        switch (result) {
            case 0:
                score[i][j] += 1;
                score[j][i] += 1;
                break;
            case 1:
                score[i][j] += 3;
                break;
            case 2:
                score[j][i] += 3;
                break;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
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
                if (i != j) {
                    resultTable.append(score[i][j]);
                } else {
                    resultTable.append("-");
                }
            }
        }

        return resultTable.toString();
    }
}
