package fileio;

public class Input {
    /** number of years that the delivery will work */
    private final int noYears;
    /** the initial santasBudget */
    private final Double santasBudget;
    /** the initial data of the delivery's workshop */
    private final InitialDataInput initialData;
    /** the annual changes of the delivery's workshop */
    private final AnnualChangesInput annualChanges;

    /**
     * constructor for input object to extract data from files
     */
    public Input(final int noYears,
                 final Double santasBudget,
                 final InitialDataInput initialData,
                 final AnnualChangesInput annualChanges) {
        this.noYears = noYears;
        this.santasBudget = santasBudget;
        this.initialData = initialData;
        this.annualChanges = annualChanges;
    }

    public final AnnualChangesInput getAnnualChanges() {
        return annualChanges;
    }

    public final Double getSantasBudget() {
        return santasBudget;
    }

    public final InitialDataInput getInitialData() {
        return initialData;
    }

    public final int getNoYears() {
        return noYears;
    }

    @Override
    public final String toString() {
        return "{"
                + "noYears=" + noYears
                + ", santasBudget=" + santasBudget
                + ", initialData=" + initialData
                + ", annualChanges=" + annualChanges
                + '}';
    }
}
