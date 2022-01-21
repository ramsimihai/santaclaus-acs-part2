package children;

import elves.Elf;
import gifts.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import scores.AverageScoreStrategy;

import java.util.ArrayList;

public class Child {
    /** id of the child to find him in the database */
    private int id;
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
    private ArrayList<Double> niceScore = new ArrayList<>();
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
    private Double niceScoreBonus;
    /** elf that does delivery of gifts for a child */
    private Elf elf;
    /** nice score based from the city this kiddo lives */
    private Double niceScoreCity;

    /**
     * constructor to create an instance of a Child with specified parameteres
     * and some of the fields will be instantiated with default values
     * @param id
     * @param lastName
     * @param firstName
     * @param age
     * @param city
     * @param niceScore
     * @param giftsPreferences
     * @param niceScoreBonus
     * @param elf
     */
    public Child(final int id,
                 final String lastName,
                 final String firstName,
                 final int age,
                 final String city,
                 final Double niceScore,
                 final ArrayList<String> giftsPreferences,
                 final Double niceScoreBonus,
                 final Elf elf) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore.add(niceScore);
        this.giftsPreferences = giftsPreferences;
        this.averageScore = 0.0;
        this.strategy = null;
        this.assignedBudget = 0.0;
        this.receivedGifts = new ArrayList<>();
        this.initialBudget = 0.0;
        this.niceScoreBonus = niceScoreBonus;
        this.elf = elf;
    }

    /**
     * default constructor
     */
    public Child() {
        this.id = 0;
        this.lastName = null;
        this.firstName = null;
        this.age = 0;
        this.city = null;
        this.giftsPreferences = null;
        this.averageScore = 0.0;
        this.strategy = null;
        this.assignedBudget = 0.0;
        this.initialBudget = 0.0;
        this.niceScoreBonus = 0.0;
        this.elf = null;
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
    public void setElf(Elf elf) {
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

    public void setNiceScoreCity(Double niceScoreCity) {
        this.niceScoreCity = niceScoreCity;
    }

    public Double getNiceScoreCity() {
        return niceScoreCity;
    }

    /**
     * creates a JSONObject based on child parameters to be added in another
     * JSONArray to create the output requested
     * @return JSONObject containing all the data requested from a child
     */
    public JSONObject getJSON() {
        JSONObject newChildJSON = new JSONObject();

        // put some infos in the JSONObject
        newChildJSON.put("id", id);
        newChildJSON.put("lastName", lastName);
        newChildJSON.put("firstName", firstName);
        newChildJSON.put("city", city);
        newChildJSON.put("age", age);

        // creating a JSONArray for the gift preferences of the child
        JSONArray giftPreferencesJSON = new JSONArray();
        for (String preference : giftsPreferences) {
            giftPreferencesJSON.add(preference);
        }
        newChildJSON.put("giftsPreferences", giftPreferencesJSON);
        newChildJSON.put("averageScore", averageScore);

        // creating a JSONArray for the nice score history of the child
        JSONArray niceScoreHistoryJSON = new JSONArray();
        for (Double score : niceScore) {
            niceScoreHistoryJSON.add(score);
        }
        newChildJSON.put("niceScoreHistory", niceScoreHistoryJSON);
        newChildJSON.put("assignedBudget", initialBudget);

        // creating a JSONArray of received gifts & a JSONObject containing the data from
        // every gift that is received by the child
        JSONArray receivedGiftsJSON = new JSONArray();
        for (Gift gift : receivedGifts) {
            JSONObject receivedGiftJSON = new JSONObject();

            receivedGiftJSON.put("productName", gift.getProductName());
            receivedGiftJSON.put("price", gift.getPrice());
            receivedGiftJSON.put("category", gift.getCategory());

            receivedGiftsJSON.add(receivedGiftJSON);
        }

        newChildJSON.put("receivedGifts", receivedGiftsJSON);

        return newChildJSON;
    }

    /**
     * toString method used for printing purposes ;_;
     */
    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", niceScore=" + niceScore +
                ", giftsPreferences=" + giftsPreferences +
                ", averageScore=" + averageScore +
                ", strategy=" + strategy +
                ", initialBudget=" + initialBudget +
                ", assignedBudget=" + assignedBudget +
                ", receivedGifts=" + receivedGifts +
                ", niceScoreBonus=" + niceScoreBonus +
                ", elf=" + elf +
                ", niceScoreCity=" + niceScoreCity +
                '}';
    }
}
