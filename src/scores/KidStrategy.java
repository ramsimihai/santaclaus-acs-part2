package scores;

import java.util.ArrayList;

public class KidStrategy implements AverageScoreStrategy {
    private final ArrayList<Double> niceScore;

    /**
     * constructor for kiddo's strategy
     * @param niceScore used for calculating the averageScore
     */
    public KidStrategy(final ArrayList<Double> niceScore) {
        this.niceScore = niceScore;
    }

    /**
     * getter of niceScore
     * @return niceScore
     */
    public ArrayList<Double> getNiceScore() {
        return niceScore;
    }

    /**
     * getter of the averageScore of a kiddo
     * @return averageScore
     */
    @Override
    public Double getAverageScore() {
        Double sum = 0.0;
        for (Double score : niceScore) {
            sum += score;
        }

        return sum / niceScore.size();
    }
}
