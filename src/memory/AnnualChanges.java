package memory;

import updates.ChangeOfTheYear;

import java.util.List;

public class AnnualChanges {
    private final List<ChangeOfTheYear> changes;

    /**
     * default constructor
     */
    public AnnualChanges() {
        changes = null;
    }

    /**
     * constructor for annual changes containing list of changes
     * @param changes
     */
    public AnnualChanges(final List<ChangeOfTheYear> changes) {
        this.changes = changes;
    }

    /**
     * getter of list of annual changes
     */
    public List<ChangeOfTheYear> getChanges() {
        return changes;
    }

    /**
     * toString method
     */
    @Override
    public String toString() {
        return "{"
                + "changes=" + changes
                + '}';
    }
}
