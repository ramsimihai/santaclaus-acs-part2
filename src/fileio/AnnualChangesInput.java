package fileio;

import java.util.List;

public class AnnualChangesInput {
    /** list of changes that are going to be made in the future */
    private final List<ChangeOfTheYearInput> changes;

    /**
     * constructor for annual changes input class
     * @param changes
     */
    public AnnualChangesInput(final List<ChangeOfTheYearInput> changes) {
        this.changes = changes;
    }

    public final List<ChangeOfTheYearInput> getChanges() {
        return changes;
    }

    @Override
    public final String toString() {
        return "{"
                + "changes=" + changes
                + '}';
    }
}
