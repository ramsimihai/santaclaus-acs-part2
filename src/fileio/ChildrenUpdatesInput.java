package fileio;

import java.util.ArrayList;

public class ChildrenUpdatesInput {
    /** id of the child that is going to be updated */
    private final int id;
    /** new niceScore to add to child's list of scores */
    private final Double niceScore;
    /** new preferences for gifts of a child */
    private final ArrayList<String> giftsPreferences;
    /** new elf for gifts delivery for a child */
    private final String elf;

    /**
     * constructor for a children update input object
     */
    public ChildrenUpdatesInput(final int id,
                                final Double niceScore,
                                final ArrayList<String> giftsPreferences,
                                final String elf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.elf = elf;
    }

    public final ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public final Double getNiceScore() {
        return niceScore;
    }

    public final int getId() {
        return id;
    }

    public String getElf() {
        return elf;
    }

    @Override
    public final String toString() {
        return "{"
                + "id=" + id
                + ", niceScore=" + niceScore
                + ", giftsPreferences=" + giftsPreferences
                + ", elf=" + elf
                + '}';
    }
}
