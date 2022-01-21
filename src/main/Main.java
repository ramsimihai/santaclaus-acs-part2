package main;

import checker.Checker;
import common.Constants;
import fileio.Input;
import fileio.InputLoader;

import fileio.Writer;
import org.json.simple.JSONObject;
import updates.ChangeOfTheYear;
import workshop.Santa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static common.Constants.SECOND_ANGEL_NUMBER;

/** The entry point to this homework. It runs the checker that tests the implementation. */
public final class Main {
    /** for coding style */
    private Main() {
    }
    /**
     * Call the main checker and the coding style checker
     * opens the tests files and also creates new output filess
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filename = file.getPath().substring(SECOND_ANGEL_NUMBER);
            String filepath = Constants.OUTPUT_PATH + filename;
            File out = new File(filepath);

            boolean isCreated = out.createNewFile();

            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1, final String filePath2) throws IOException {
        // creates a new inputLoader to extract data from a path
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        // creates a writer to open a file from a path
        Writer fileWriter = new Writer(filePath2);
        JSONObject objectResult = new JSONObject();

        // gets a santa instance that will do the work of delivering gifts to children
        Santa santa = Santa.getInstance();

        // extracts data from the input and put it into the santa's database
        santa.setSantasBudget(input.getSantasBudget());
        santa.setNoYears(input.getNoYears());
        santa.addInitialData(input.getInitialData());
        santa.addAnnualChanges(input.getAnnualChanges());

        // adds the initial change (of the first year) to the annual changes
        // for automation purposes
        ChangeOfTheYear initialChange = santa.addChange();
        santa.getAnnualChanges().getChanges().add(0, initialChange);
        santa.setActualYear(0);

        // uploads the output into a JSON and write into the output file
        objectResult.put("annualChildren", santa.startDelivery());
        fileWriter.closeJSON(objectResult);
    }
}
