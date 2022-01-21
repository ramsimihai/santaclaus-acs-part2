package scores;

import java.util.ArrayList;

import static common.Constants.BABY_NUMBER;
import static common.Constants.KID_NUMBER;
import static common.Constants.TEEN_NUMBER;


public class AverageScoreStrategyFactory {
    /**
     * default factory to create a strategy of the types provided:
     * BABY || KID || TEEN || YOUNG ADULT
     * @param age used to get the type of strategy
     * @param niceScore used for calculating the averageScore
     * @return
     */
    public AverageScoreStrategy createStrategy(final int age,
                                               final ArrayList<Double> niceScore) {
        if (age < BABY_NUMBER) {
            return new BabyStrategy(niceScore);
        } else if (age >= BABY_NUMBER && age < KID_NUMBER) {
            return new KidStrategy(niceScore);
        } else if (age >= KID_NUMBER && age <= TEEN_NUMBER) {
            return new TeenStrategy(niceScore);
        } else {
            return new YoungAdultStrategy(niceScore);
        }
    }
}
