package scores;

import java.util.ArrayList;

public class TeenStrategy implements AverageScoreStrategy {
    private final ArrayList<Double> niceScore;

    /**
     * constructor for TeenStrategy
     * @param niceScore
     */
    public TeenStrategy(final ArrayList<Double> niceScore) {
        this.niceScore = niceScore;
    }

    /**
     * getter for niceScore
     * @return niceScore
     */
    public ArrayList<Double> getNiceScore() {
        return niceScore;
    }

    /**
     * calculates the average score of a teenager
     * @return averageScore
     */
    @Override
    public Double getAverageScore() {
        Double averageScore = 0.0;

        int noScores = 0;
        int i = 1;
        for (Double score : niceScore) {
            averageScore += i * score;
            noScores += i;
            i++;
        }

        return averageScore / noScores;
    }
}
