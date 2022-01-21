package utils;

import org.json.simple.JSONArray;

import java.util.ArrayList;

public final class Utils {
    /**
     * default constructor for checkstyle
     */
    private Utils() {
    }

    /**
     * Transforms an array of JSONs into an array of strings
     * @param array of JSONs
     * @return a list of strings
     */
    public static ArrayList<String> convertJSONArray(final JSONArray array) {
        if (array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }
}
