package workshop;

import delivery.DeliveryByID;
import delivery.DeliveryStrategyFactory;
import elves.Elf;
import elves.ElvesFactory;
import elves.YellowElf;
import fileio.AnnualChangesInput;
import fileio.ChangeOfTheYearInput;
import fileio.ChildrenInput;
import fileio.ChildrenUpdatesInput;
import fileio.GiftInput;
import fileio.InitialDataInput;

import children.Child;
import children.ChildUpdates;
import gifts.Gift;
import memory.AnnualChanges;
import memory.InitialData;
import updates.ChangeOfTheYear;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import scores.AverageScoreStrategy;
import scores.AverageScoreStrategyFactory;

import java.util.*;
import java.util.stream.Collectors;

import static common.Constants.TEEN_NUMBER;

/** singleton santa, but we are santa and we are evil (jk) */
public final class Santa {
    /** the instance of the singleton */
    private static Santa santa = null;
    /** the noYears that the santa's gone and the program will do the job */
    private int noYears = 0;
    /** santas budget per year*/
    private Double santasBudget = 0.0;
    /** initial data used to extract datas and add them to annual changes
     * for easy implementation of delivery concept */
    private InitialData initialData = new InitialData();
    /** annual changes that will be done over the noYears */
    private AnnualChanges annualChanges = new AnnualChanges();
    /** list of all santa's good kiddos*/
    private List<Child> children = new ArrayList<>();
    /** list of available gifts to be delivered to good kiddos */
    private List<Gift> availableGifts = new ArrayList<>();
    /** a field that will contain how many years passed since the santa's gone*/
    private int actualYear = 0;
    /** a budgetUnit is just something to calculate the santas actual budget */
    private Double budgetUnit = 0.0;
    /** map of cities and all the kids' average score that live in that city */
    private Map<String, ArrayList<Double>> citiesMap = new HashMap<>();

    /**
     * private default constructor so that this should be a singleton
     */
    private Santa() {
    }

    /**
     * setter of santas annual budget
     */
    public void setSantasBudget(final Double santasBudget) {
        this.santasBudget = santasBudget;
    }

    /**
     * setter of noYears to work as Santa's replacement
     */
    public void setNoYears(final int noYears) {
        this.noYears = noYears;
    }

    /**
     * @return gets the instance of Database
     */
    public static Santa getInstance() {
        if (santa == null) {
            santa = new Santa();
        }

        return santa;
    }

    /**
     * increments ages of all childrens from the list
     */
    public void incrementsAge() {
        for (Child child : children) {
            child.setAge(child.getAge() + 1);
        }
    }

    /**
     * adds all the new children from the list of new kiddos of the annual change
     * @param change of the Year
     */
    public void addNewChildren(final ChangeOfTheYear change) {
        if (actualYear == 0 || change.getNewChildren() == null) {
            return;
        }

        for (Child child : change.getNewChildren()) {
            children.add(child);
        }
    }

    /**
     * deletes all the young adults from the list of children
     * cause they are naughty kiddos and dont need our gifts |-:-|
     */
    public void deleteYoungAdults() {
        children.removeIf(child -> child.getAge() > TEEN_NUMBER);
    }

    /**
     * sort all the children in the list by the id
     * this is redundant on the tests but there could be a possibility where in the list
     * of new children, they wouldnt be ordered in ascending order by id (so here i am)
     */
    public void sortChildren() {
        Comparator<Child> idComparator = Comparator.comparing(Child::getId);

        children = children.stream().sorted(idComparator).collect(Collectors.toList());
    }

    /**
     * sorts all the gifts from the list so that every first appareance of gifts from
     * a specified category will be the lowest priced one and directly delivered to some weird
     * kiddo
     */
    public void sortGifts() {
        Comparator<Gift> priceComparator = Comparator.comparing(Gift::getPrice);
        Comparator<Gift> categoryComparator = Comparator.comparing(Gift::getCategory)
                                                        .thenComparing(priceComparator);

        availableGifts = availableGifts.stream().sorted(categoryComparator)
                                                .collect(Collectors.toList());
    }

    /**
     * updates all the children given by the change of the year if they exists
     * in santa's list
     * @param change of the YEAR
     */
    public void updateChildren(final ChangeOfTheYear change) {
        if (actualYear == 0 || change.getNewUpdates() == null) {
            return;
        }

        // iterates through all the changes of the corresponding year
        List<ChildUpdates> updates = change.getNewUpdates();
        for (ChildUpdates update : updates) {
            Child updatedChild = null;

            // iterates through list of the children and gets the corresponding child
            for (Child child : children) {
                if (update.getId() == child.getId()) {
                    updatedChild = child;

                    break;
                }
            }

            if (updatedChild != null) {
                // updates the niceScore
                if (update.getNiceScore() != null) {
                    updatedChild.getNiceScore().add(update.getNiceScore());
                }

                // updates the elf of the child
                if (update.getNewElf() != null) {
                    updatedChild.setElf(update.getNewElf());
                }

                // gets the old preferences
                List<String> preferences = updatedChild.getGiftsPreferences();

                // new list of preferences
                List<String> duplicatedNewPreferences = update.getGiftsPreferences();
                // unique list of preferences (from the new one)
                List<String> uniqueNewPreferences = new ArrayList<>(
                        new LinkedHashSet<>(duplicatedNewPreferences));

                // adds the every preference from the end of new preferences
                // to the end of the old preferences
                Collections.reverse(uniqueNewPreferences);
                for (String preference : uniqueNewPreferences) {
                    if (preferences.contains(preference)) {
                        preferences.remove(preference);
                    }

                    preferences.add(0, preference);
                }
            }
        }
    }

    /**
     * adds new gifts in the list of the gifts
     * @param change of the YEAR
     */
    public void addNewGifts(final ChangeOfTheYear change) {
        if (actualYear == 0 || change.getNewGifts() == null) {
            return;
        }

        for (Gift gift : change.getNewGifts()) {
            availableGifts.add(gift);
        }
    }

    /**
     * initialize the budget of santa in the corresponding year
     * and also the budget given for every children
     * @param change of the YEAR
     */
    public void initializeBudget(final ChangeOfTheYear change) {
        santasBudget = change.getNewSantaBudget();
        Double overallAverageScore = 0.0;
        for (Child child : children) {
            Double averageScoreChild = child.getAverageScore();

            averageScoreChild += averageScoreChild * child.getNiceScoreBonus() / 100;

            if (averageScoreChild >= 10.0) {
                averageScoreChild = 10.0;
            }

            child.setAverageScore(averageScoreChild);

            overallAverageScore += averageScoreChild;
        }

        if (overallAverageScore != 0.0) {
            budgetUnit = santasBudget / overallAverageScore;
        } else {
            budgetUnit = santasBudget;
        }

        // initializes the assignedBudget that is going to be used in delivery
        // and also the initialBudget
        for (Child child : children) {
            child.setBudget(child.getAverageScore() * budgetUnit);
            child.setInitialBudget(child.getAverageScore() * budgetUnit);
            child.getElf().setBudget(child.getAverageScore() * budgetUnit);
        }
    }

    public void exceptYellowElfsWork() {
        for (Child child : children) {
            if (!child.getElf().getName().equals("yellow")) {
                Double childCash = child.getElf().execute();
                child.setBudget(childCash);
                child.setInitialBudget(childCash);
            }
        }
    }

    public void onlyYellowElfsWork() {
        for (Child child : children) {
            if (child.getElf().getName().equals("yellow")) {
                child.getElf().execute();
                if (child.getElf() instanceof YellowElf) {
                    ((YellowElf) child.getElf()).setUnluckyKiddo(child);
                }
            }
        }
    }

    /**
     * assigns to every children a strategy in function of the category of ages
     * then calculates the averageScore of every children
     */
    public void getTypesOfChildren() {
        AverageScoreStrategyFactory factory = new AverageScoreStrategyFactory();

        for (Child child : children) {
            AverageScoreStrategy newStrategy = factory.createStrategy(child.getAge(),
                    child.getNiceScore());
            child.setStrategy(newStrategy);
            child.setAverageScore(child.getStrategy().getAverageScore());
        }
    }

    /**
     * does a year delivery: clearing the old received gifts, gives to every child the lowest
     * priced gift from the wanted categories in function of the assigned budget for his as#
     */
    public void yearDelivery() {
        for (Child child : children) {
            child.getReceivedGifts().clear();
            ArrayList<String> preferences = child.getGiftsPreferences();

            // iterates through child preferences
            for (String preferredGift : preferences) {
                int i = 0;
                for (Gift singleGift : availableGifts) {
                    // deliver gifts to his as#
                    if (singleGift.getCategory().equals(preferredGift)
                            && singleGift.getPrice() < child.getAssignedBudget()) {
                        child.getReceivedGifts().add(singleGift);
                        child.setBudget(child.getAssignedBudget()
                                - singleGift.getPrice());

                        // decrease gifts quantity and removes
                        availableGifts.get(i).setQuantity(availableGifts.get(i).getQuantity() - 1);
                        if (availableGifts.get(i).getQuantity() == 0) {
                            availableGifts.remove(singleGift);
                        }

                        break;
                    }
                    i++;
                }
            }
        }
    }

    /**
     * create the output in JSON format by maintaing a specified format
     * @return a JSONArray used to build the output
     */
    public JSONArray getOutput() {
        JSONArray childrenJSON = new JSONArray();
        for (Child child : children) {
            JSONObject childJSON = child.getJSON();
            childrenJSON.add(childJSON);
        }

        return childrenJSON;
    }

    public void addsNiceScoreCities() {
        citiesMap.clear();

        for (Child child : children) {
            if (citiesMap.containsKey(child.getCity())) {
                ArrayList<Double> entry = citiesMap.get(child.getCity());
                entry.add(child.getAverageScore());

            } else {
                ArrayList<Double> newScoresCityList = new ArrayList<>();
                newScoresCityList.add(child.getAverageScore());
                citiesMap.put(child.getCity(), newScoresCityList);
            }
        }
    }

    public void calculatesNiceScoreCityChild() {
        for (Child child : children) {
            if (citiesMap.containsKey(child.getCity())) {
                Double average = 0.0;
                for (int i = 0; i < citiesMap.get(child.getCity()).size(); i++) {
                    average += citiesMap.get(child.getCity()).get(i);
                }
                average = average / citiesMap.get(child.getCity()).size();

                child.setNiceScoreCity(average);
            }
        }
    }
    /**
     * MY JOB STARSTS! NOW! delivery in noYears gifts to good kiddos 100%
     * @return a JSONArray used to build the output
     */
    public JSONArray startDelivery() {
        JSONArray annualChildrenJSON = new JSONArray();

        // iterates through years
        actualYear = 0;
        while (true) {
            ChangeOfTheYear annualChange = annualChanges.getChanges().get(actualYear);

            // makes children changes
            addNewChildren(annualChange);
            deleteYoungAdults();
            sortChildren();
            updateChildren(annualChange);

            // makes gifts changes
            addNewGifts(annualChange);
            sortGifts();

            // calculates the averageScore and initializes the budget of santa & every children
            getTypesOfChildren();
            initializeBudget(annualChange);

            // calculates the budget after every elf is doing their job (except yellow)
            exceptYellowElfsWork();

            // delivery of the corresponding year
            annualChange.getStrategy().delivery();

            // delivery for unlucky kiddos
            onlyYellowElfsWork();

            sortChildren();
            // extract the output
            JSONObject objJSON = new JSONObject();
            objJSON.put("children", getOutput());
            annualChildrenJSON.add(objJSON);

            if (actualYear == noYears) {
                break;
            }

            // increments the kiddos age cause this year has ended! HAPPY NEW YEAR!
            incrementsAge();
            actualYear++;
        }

        return annualChildrenJSON;
    }

    /**
     * add initial data from the extracted initial data input
     * @param input aka an InitialData object that has the first year situation
     */
    public void addInitialData(final InitialDataInput input) {
        if (input == null) {
            return;
        }

        List<ChildrenInput> childrenInput = input.getChildrenList();
        List<GiftInput> giftsInput = input.getGiftsList();
        List<Child> childrenList = santa.addChildren(childrenInput);
        List<Gift> giftsList = santa.addGifts(giftsInput);

        santa.setChildren(childrenList);
        santa.setAvailableGifts(giftsList);
        initialData = new InitialData(childrenList, giftsList);
    }

    /**
     * add the extracted annual changes to the database
     * @param input aka an AnnualChanges object that has the all years situation
     */
    public void addAnnualChanges(final AnnualChangesInput input) {
        if (input == null) {
            return;
        }

        List<ChangeOfTheYearInput> changesInput = input.getChanges();
        List<ChangeOfTheYear> changes = new ArrayList<>();
        DeliveryStrategyFactory deliveryStrategyFactory = new DeliveryStrategyFactory();

        for (ChangeOfTheYearInput oneChange : changesInput) {
            ArrayList<Child> childrenList = santa.addChildren(oneChange.getNewChildren());
            ArrayList<Gift> giftsList = santa.addGifts(oneChange.getNewGifts());
            ArrayList<ChildUpdates> updates = santa.addUpdates(oneChange.getNewUpdates());

            ChangeOfTheYear newChange = new ChangeOfTheYear(
                    oneChange.getNewSantaBudget(),
                    giftsList,
                    childrenList,
                    updates,
                    deliveryStrategyFactory
                            .createDeliveryStrategy(oneChange.getStrategy())
            );
            changes.add(newChange);
        }

        annualChanges = new AnnualChanges(changes);
    }

    /**
     * adds the children extracted from the input
     * @param childrenInput aka list of initial children
     * @return an ArrayList<Child> to be used later
     */
    public ArrayList<Child> addChildren(final List<ChildrenInput> childrenInput) {
        ArrayList<Child> initialChildren = new ArrayList<>();
        ElvesFactory elvesFactory = new ElvesFactory();
        for (ChildrenInput childInput : childrenInput) {
            Child newChild = new Child(
                    childInput.getId(),
                    childInput.getLastName(),
                    childInput.getFirstName(),
                    childInput.getAge(),
                    childInput.getCity(),
                    childInput.getNiceScore(),
                    childInput.getGiftsPreferences(),
                    childInput.getNiceScoreBonus(),
                    elvesFactory.createElf(childInput.getElf())
            );
            initialChildren.add(newChild);
        }

        return initialChildren;
    }

    /**
     * adds the gifts extracted from the input
     * @param giftsInput aka list of initial gifts
     * @return an ArrayList<Gift> to be used later
     */
    public ArrayList<Gift> addGifts(final List<GiftInput> giftsInput) {
        ArrayList<Gift> gifts = new ArrayList<>();

        for (GiftInput giftInput : giftsInput) {
            Gift newGift = new Gift(
                    giftInput.getProductName(),
                    giftInput.getPrice(),
                    giftInput.getCategory(),
                    giftInput.getQuantity()
            );

            gifts.add(newGift);
        }

        return gifts;
    }

    /**
     * adds the children's updates extracted from the input
     * @param updatesInput aka list of initial updates
     * @return an ArrayList<ChildrenUpdates> to be used later
     */
    public ArrayList<ChildUpdates> addUpdates(final List<ChildrenUpdatesInput> updatesInput) {
        ArrayList<ChildUpdates> updates = new ArrayList<>();
        ElvesFactory elvesFactory = new ElvesFactory();

        for (ChildrenUpdatesInput updateInput : updatesInput) {
            Elf newElf = elvesFactory.createElf(updateInput.getElf());

            ChildUpdates newUpdate = new ChildUpdates(
                    updateInput.getId(),
                    updateInput.getNiceScore(),
                    updateInput.getGiftsPreferences(),
                    newElf
            );

            updates.add(newUpdate);
        }
        return updates;
    }

    /**
     * adds a change of the YEAR (to add the Annual Changes)
     * @return
     */
    public ChangeOfTheYear addChange() {
        ChangeOfTheYear newChange = new ChangeOfTheYear(santasBudget,
                santa.getInitialData().getGiftsList(),
                santa.getInitialData().getChildrenList(),
                new ArrayList<>(),
                new DeliveryByID("id")
        );

        return newChange;
    }

    /**
     * getter of initial data
     */
    public InitialData getInitialData() {
        return initialData;
    }

    /**
     * getter of annual changes
     */
    public AnnualChanges getAnnualChanges() {
        return annualChanges;
    }

    /**
     * getter of the list of children
     */
    public List<Child> getChildren() {
        return children;
    }

    public List<Gift> getAvailableGifts() {
        return availableGifts;
    }

    /**
     * setter of actual year
     */
    public void setActualYear(final int actualYear) {
        this.actualYear = actualYear;
    }

    /**
     * setter of availableGifts
     */
    public void setAvailableGifts(final List<Gift> availableGifts) {
        this.availableGifts = availableGifts;
    }

    /**
     * setter of the list of children
     */
    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    /**
     * toString method for... (ok, you got me, i dont freakin' know)
     */
    @Override
    public String toString() {
        return "{"
                + "noYears=" + noYears
                + ", santasBudget=" + santasBudget
                + ", initialData=" + initialData
                + ", annualChanges=" + annualChanges
                + '}';
    }
}
