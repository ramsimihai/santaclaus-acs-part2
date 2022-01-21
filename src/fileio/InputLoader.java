package fileio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputLoader {
    /** the file where the data will be extracted from */
    private final String inputPath;

    /**
     * constructor for an input loader
     * @param inputPath
     */
    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * reads data from a new input loader based on data from tests
     * takes the JSONObject and put the fields to a new object instantied of
     * class Input with the following parameters:
     *
     * @noYears noYears for how much the workshop will work
     * @santaBudget of the first year
     * @initialData all the data of the first simulation of the workshop
     * @annualChanges changes and deliveries over the noYears
     * @return an Input object with the fresh new data
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        int noYears = 0;
        Double santaBudget = 0.0;
        InitialDataInput initialData = null;
        AnnualChangesInput annualChanges = null;

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));

            // gets the noYears and santaBudget
            noYears = Integer.parseInt(jsonObject.get("numberOfYears").toString());
            santaBudget = Double.parseDouble(jsonObject.get("santaBudget").toString());

            // starts to extract data from the rest of the JSON
            // by creating JSON Objects & Arrays so that every field could be extracted
            JSONObject jsonInitialData = (JSONObject) jsonObject.get("initialData");
            JSONArray jsonChanges = (JSONArray) jsonObject.get("annualChanges");

            // gets the initial data from the extracted JSON
            if (jsonInitialData != null) {
                // prepare new objects to be instantiated with the extracted data
                ArrayList<ChildrenInput> children = new ArrayList<>();
                ArrayList<GiftInput> gifts = new ArrayList<>();

                // iterates through initial children list of the JSON
                JSONArray jsonInitialChildren = (JSONArray) jsonInitialData.get("children");
                for (Object jsonInitKiddo : jsonInitialChildren) {
                    // adds every child into the initial data parameter
                    children.add(new ChildrenInput(
                          Integer.parseInt(((JSONObject) jsonInitKiddo).get("id").toString()),
                          (String) ((JSONObject) jsonInitKiddo).get("lastName"),
                          (String) ((JSONObject) jsonInitKiddo).get("firstName"),
                          Integer.parseInt(((JSONObject) jsonInitKiddo).get("age").toString()),
                          (String) ((JSONObject) jsonInitKiddo).get("city"),
                          Double.parseDouble(((JSONObject) jsonInitKiddo)
                                  .get("niceScore").toString()),
                          Utils.convertJSONArray((JSONArray) ((JSONObject) jsonInitKiddo)
                                  .get("giftsPreferences")),
                          Double.parseDouble(((JSONObject) jsonInitKiddo)
                                    .get("niceScoreBonus").toString()),
                          (String) ((JSONObject) jsonInitKiddo).get("elf")
                    ));
                }

                // iterates through initial gifts list of the JSON
                JSONArray jsonInitialGifts = (JSONArray) jsonInitialData.get("santaGiftsList");
                for (Object jsonInitGift : jsonInitialGifts) {
                    // adds every gift into the initial data parameter
                    gifts.add(new GiftInput(
                            (String) ((JSONObject) jsonInitGift).get("productName"),
                            Double.parseDouble(((JSONObject) jsonInitGift).get("price").toString()),
                            (String) ((JSONObject) jsonInitGift).get("category"),
                            Integer.parseInt(((JSONObject) jsonInitGift)
                                    .get("quantity").toString())
                    ));
                }

                // instantiate the freshly new initial data object
                initialData = new InitialDataInput(children, gifts);
            } else {
                System.out.println("There isn't any initial data");
            }

            // gets the annual changes from the extracted JSON
            if (jsonChanges != null) {
                // prepare the new changes list
                List<ChangeOfTheYearInput> changes = new ArrayList<>();

                // iterates through every change
                for (Object jsonChange : jsonChanges) {
                    // prepare the new lists to put the extracted data
                    ArrayList<ChildrenInput> children = new ArrayList<>();
                    ArrayList<GiftInput> gifts = new ArrayList<>();
                    ArrayList<ChildrenUpdatesInput> childrenUpdates = new ArrayList<>();

                    // extract the new santa budget, type of strategy used for delivery
                    // and the JSON lists
                    Double newSantaBudget = Double.parseDouble(((JSONObject) jsonChange)
                            .get("newSantaBudget").toString());
                    JSONArray jsonNewGifts = (JSONArray) ((JSONObject) jsonChange)
                            .get("newGifts");
                    JSONArray jsonNewChildren = (JSONArray) ((JSONObject) jsonChange)
                            .get("newChildren");
                    JSONArray jsonChildrenUpdates = (JSONArray) ((JSONObject) jsonChange)
                            .get("childrenUpdates");
                    String typeOfStrategy = (String) ((JSONObject) jsonChange).get("strategy");

                    // starts iterating through JSONObject (gifts)
                    for (Object jsonNewGift : jsonNewGifts) {
                        // add the gifts to the corresponding list
                        gifts.add(new GiftInput(
                                (String) ((JSONObject) jsonNewGift).get("productName"),
                                Double.parseDouble(((JSONObject) jsonNewGift)
                                        .get("price").toString()),
                                (String) ((JSONObject) jsonNewGift).get("category"),
                                Integer.parseInt(((JSONObject) jsonNewGift)
                                        .get("quantity").toString())
                        ));
                    }

                    // starts iterating through JSONObject (children)
                    for (Object jsonNewKiddo : jsonNewChildren) {
                        // add the children to the corresponding list
                        children.add(new ChildrenInput(
                                Integer.parseInt(((JSONObject) jsonNewKiddo)
                                        .get("id").toString()),
                                (String) ((JSONObject) jsonNewKiddo).get("lastName"),
                                (String) ((JSONObject) jsonNewKiddo).get("firstName"),
                                Integer.parseInt(((JSONObject) jsonNewKiddo)
                                        .get("age").toString()),
                                (String) ((JSONObject) jsonNewKiddo).get("city"),
                                Double.parseDouble(((JSONObject) jsonNewKiddo)
                                        .get("niceScore").toString()),
                                Utils.convertJSONArray((JSONArray) ((JSONObject) jsonNewKiddo)
                                        .get("giftsPreferences")),
                                Double.parseDouble(((JSONObject) jsonNewKiddo)
                                        .get("niceScoreBonus").toString()),
                                (String) ((JSONObject) jsonNewKiddo).get("elf")
                        ));
                    }

                    // starts iterating through JSONObject (children updates)
                    for (Object jsonChildrenUpdate : jsonChildrenUpdates) {
                        // prepare a temporary niceScore to treat a corner case
                        // and extracts it
                        Double temporaryNiceScore = null;
                        if (((JSONObject) jsonChildrenUpdate).get("niceScore") !=  null) {
                            temporaryNiceScore = Double.parseDouble(
                                    ((JSONObject) jsonChildrenUpdate).get("niceScore").toString());
                        }

                        // extract the updates from the json and adds them to the list
                        childrenUpdates.add(new ChildrenUpdatesInput(
                                Integer.parseInt(((JSONObject) jsonChildrenUpdate)
                                        .get("id").toString()),
                                temporaryNiceScore,
                                Utils.convertJSONArray(
                                        (JSONArray) ((JSONObject) jsonChildrenUpdate)
                                                .get("giftsPreferences")),
                                (String) ((JSONObject) jsonChildrenUpdate).get("elf")
                        ));
                    }

                    // create a new change with the extracted data and add it to the list of changes
                    changes.add(new ChangeOfTheYearInput(
                            newSantaBudget, gifts, children, childrenUpdates, typeOfStrategy));
                }
                annualChanges = new AnnualChangesInput(changes);
            } else {
                System.out.println("There aren't new changes");
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(noYears, santaBudget, initialData, annualChanges);
    }
}
