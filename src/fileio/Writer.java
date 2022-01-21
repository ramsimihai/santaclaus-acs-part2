package fileio;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    /** output file */
    private final FileWriter file;

    /**
     * constructor for writer object
     * @param path to the file where the JSONObject will be written
     * @throws IOException in cause it error occurs
     */
    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    /**
     * close the program by writing the jsonObject into the file
     * @param object
     */
    public void closeJSON(final JSONObject object) {
        try {
            file.write(object.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
