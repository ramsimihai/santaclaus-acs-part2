package scores;

import java.util.ArrayList;

public class YoungAdultStrategy implements AverageScoreStrategy {
    private final ArrayList<Double> niceScore;

    /**
     * constructor for young adults' strategy
     * @param niceScore used for calculating the averageScore
     */
    public YoungAdultStrategy(final ArrayList<Double> niceScore) {
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
     * getter of averageScore
     * @return null
     */
    @Override
    public Double getAverageScore() {
        return null;
    }
}
