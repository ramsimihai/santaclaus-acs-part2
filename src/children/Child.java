package children;

import elves.Elf;
import gifts.Gift;
import scores.AverageScoreStrategy;

import java.util.ArrayList;

public final class Child {
    /** id of the child to find him in the database */
    private final int id;
    /** last name of the child */
    private final String lastName;
    /** first name of the child */
    private final String firstName;
    /** age of the child */
    private int age;
    /** city where the child lives (weirdos v2) */
    private final String city;
    /**
     * an arraylist of niceScores
     * a niceScore is added every year
     */
    private final ArrayList<Double> niceScore;
    /** arraylist of gift preferences from where the child will get gifts */
    private final ArrayList<String> giftsPreferences;
    /** an average score calculated based on a strategy */
    private Double averageScore;
    /** a strategy to calculate the average score of a children based on his age */
    private AverageScoreStrategy strategy;
    /** initial budget given in every year to see how much the santa can spent */
    private Double initialBudget;
    /** assigned budget used to check how many gifts can be given */
    private Double assignedBudget;
    /** a list of received gifts in every year by a children */
    private ArrayList<Gift> receivedGifts;
    /** extra niceScore used to calculate averageScore */
    private final Double niceScoreBonus;
    /** elf that does delivery of gifts for a child */
    private Elf elf;
    /** nice score based from the city this kiddo lives */
    private Double niceScoreCity;

    public static class ChildBuilder {
        private final int id;
        private final String lastName;
        private final String firstName;
        private final int age;
        private final String city;
        private final ArrayList<Double> niceScore = new ArrayList<>();
        private final ArrayList<String> giftsPreferences;
        private Double averageScore = 0.0;
        private AverageScoreStrategy strategy;
        private final Double initialBudget = 0.0;
        private Double assignedBudget = 0.0;
        private ArrayList<Gift> receivedGifts = new ArrayList<>();
        private Double niceScoreBonus = 0.0;
        private Elf elf;

        public ChildBuilder(final int id,
                            final String lastName,
                            final String firstName,
                            final int age,
                            final String city,
                            final Double niceScore,
                            final ArrayList<String> giftsPreferences) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
            this.age = age;
            this.city = city;
            this.niceScore.add(niceScore);
            this.giftsPreferences = giftsPreferences;
        }

        /**
         * builder setter for averageScore
         */
        public final ChildBuilder averageScore(final Double builderAverageScore) {
            this.averageScore = builderAverageScore;
            return this;
        }

        /**
         * builder setter for strategy
         */
        public final ChildBuilder strategy(final AverageScoreStrategy builderStrategy) {
            this.strategy = builderStrategy;
            return this;
        }

        /**
         * builder setter for assignedBudget
         */
        public final ChildBuilder assignedBudget(final Double builderAssignedBudget) {
            this.assignedBudget = builderAssignedBudget;
            return this;
        }

        /**
         * builder setter for receivedGifts
         */
        public final ChildBuilder receivedGifts(final ArrayList<Gift> builderReceivedGifts) {
            this.receivedGifts = builderReceivedGifts;
            return this;
        }

        /**
         * builder setter for niceScoreBonus
         */
        public final ChildBuilder niceScoreBonus(final Double builderNiceScoreBonus) {
            this.niceScoreBonus = builderNiceScoreBonus;
            return this;
        }

        /**
         * builder setter for elf
         */
        public final ChildBuilder builderElf(final Elf builderElf) {
            this.elf = builderElf;
            return this;
        }

        /**
         * builder setter for niceScoreCity
         */
        public final ChildBuilder niceScoreCity() {
            return this;
        }

        /**
         * builder for Child instance
         */
        public final Child build() {
            return new Child(this);
        }
    }

    /**
     * constructor to create an instance of a Child with specified parameters
     * extracted from a childBuilder instance that already build the fields
     * @param childBuilder
     */
    private Child(final ChildBuilder childBuilder) {
        this.id = childBuilder.id;
        this.lastName = childBuilder.lastName;
        this.firstName = childBuilder.firstName;
        this.age = childBuilder.age;
        this.city = childBuilder.city;
        this.niceScore = childBuilder.niceScore;
        this.giftsPreferences = childBuilder.giftsPreferences;
        this.averageScore = childBuilder.averageScore;
        this.strategy = childBuilder.strategy;
        this.assignedBudget = childBuilder.assignedBudget;
        this.receivedGifts = childBuilder.receivedGifts;
        this.initialBudget = childBuilder.initialBudget;
        this.niceScoreBonus = childBuilder.niceScoreBonus;
        this.elf = childBuilder.elf;
    }

    /**
     * getter of kiddo's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter of kiddo's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getter of kiddo's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getter of kiddo's age
     */
    public int getAge() {
        return age;
    }

    /**
     * setter of kiddo's age
     */
    public void setAge(final int age) {
        this.age = age;
    }

    /**
     * getter of city where a kiddo lives (weirdos v3)
     */
    public String getCity() {
        return city;
    }

    /**
     * getter of niceScore array list
     */
    public ArrayList<Double> getNiceScore() {
        return niceScore;
    }

    /**
     * getter of gifts preferences
     */
    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * getter of average score
     */
    public Double getAverageScore() {
        return averageScore;
    }

    /**
     * getter of assigned budget
     */
    public Double getAssignedBudget() {
        return assignedBudget;
    }

    /**
     * setter of assigned budget
     */
    public void setBudget(final Double budget) {
        this.assignedBudget = budget;
    }

    /**
     * getter of the child's strategy
     */
    public AverageScoreStrategy getStrategy() {
        return strategy;
    }

    /**
     * setter of average Score
     */
    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * setter of a strategy
     */
    public void setStrategy(final AverageScoreStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * getter of received gifts by a children
     */
    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * setter of received gifts by a children
     */
    public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /**
     * setter of initial budget
     */
    public void setInitialBudget(final Double initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     * setter of a new elf from a specified change to the child
     */
    public void setElf(final Elf elf) {
        this.elf = elf;
    }

    /**
     * getter of child's elf instance
     */
    public Elf getElf() {
        return elf;
    }

    /**
     * getter of child's initial niceScoreBonus
     */
    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    /**
     * setter of child's niceScoreCity
     */
    public void setNiceScoreCity(final Double niceScoreCity) {
        this.niceScoreCity = niceScoreCity;
    }

    /**
     * getter of child's niceScoreCity
     */
    public Double getNiceScoreCity() {
        return niceScoreCity;
    }

    /**
     * getter of child's initial budget
     */
    public Double getInitialBudget() {
        return initialBudget;
    }

    /**
     * toString method used for printing purposes ;_;
     */
    @Override
    public String toString() {
        return "{"
                + "id=" + id
                + ", lastName='" + lastName + '\''
                + ", firstName='" + firstName + '\''
                + ", age=" + age
                + ", city='" + city + '\''
                + ", niceScore=" + niceScore
                + ", giftsPreferences=" + giftsPreferences
                + ", averageScore=" + averageScore
                + ", strategy=" + strategy
                + ", initialBudget=" + initialBudget
                + ", assignedBudget=" + assignedBudget
                + ", receivedGifts=" + receivedGifts
                + ", niceScoreBonus=" + niceScoreBonus
                + ", elf=" + elf
                + ", niceScoreCity=" + niceScoreCity
                + '}';
    }
}
