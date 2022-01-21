package scores;

import java.util.ArrayList;

import static common.Constants.ANGEL_NUMBER;

public class BabyStrategy implements AverageScoreStrategy {
    private final ArrayList<Double> niceScore;

    /**
     * constructor for babies' strategy
     * @param niceScore
     */
    public BabyStrategy(final ArrayList<Double> niceScore) {
        this.niceScore = niceScore;
    }

    /**
     * getter of niceScore
     * @return
     */
    public ArrayList<Double> getNiceScore() {
        return niceScore;
    }

    /**
     * implementation to get averageScore of babies
     * @return 10 cause they are precious beans
     */
    @Override
    public Double getAverageScore() {
        return ANGEL_NUMBER;
    }
}
