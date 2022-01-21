package output;

import children.Child;
import gifts.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import workshop.Santa;

public class ChildrenOutput {
    /**
     * creates a JSONObject based on child parameters to be added in another
     * JSONArray to create the output requested
     * @return JSONObject containing all the data requested from a child
     */
    public JSONObject getJSON(final Child child) {
        JSONObject newChildJSON = new JSONObject();

        // put some infos in the JSONObject
        newChildJSON.put("id", child.getId());
        newChildJSON.put("lastName", child.getLastName());
        newChildJSON.put("firstName", child.getFirstName());
        newChildJSON.put("city", child.getCity());
        newChildJSON.put("age", child.getAge());

        // creating a JSONArray for the gift preferences of the child
        JSONArray giftPreferencesJSON = new JSONArray();
        for (String preference : child.getGiftsPreferences()) {
            giftPreferencesJSON.add(preference);
        }
        newChildJSON.put("giftsPreferences", giftPreferencesJSON);
        newChildJSON.put("averageScore", child.getAverageScore());

        // creating a JSONArray for the nice score history of the child
        JSONArray niceScoreHistoryJSON = new JSONArray();
        for (Double score : child.getNiceScore()) {
            niceScoreHistoryJSON.add(score);
        }
        newChildJSON.put("niceScoreHistory", niceScoreHistoryJSON);
        newChildJSON.put("assignedBudget", child.getInitialBudget());

        // creating a JSONArray of received gifts & a JSONObject containing the data from
        // every gift that is received by the child
        JSONArray receivedGiftsJSON = new JSONArray();
        for (Gift gift : child.getReceivedGifts()) {
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
     * create the output in JSON format by maintaing a specified format
     * @return a JSONArray used to build the output
     */
    public static JSONArray getOutput() {
        JSONArray childrenJSON = new JSONArray();
        for (Child child : Santa.getInstance().getChildren()) {
            JSONObject childJSON = new ChildrenOutput().getJSON(child);
            childrenJSON.add(childJSON);
        }

        return childrenJSON;
    }
}
