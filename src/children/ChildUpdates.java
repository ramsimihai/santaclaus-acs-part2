package children;

import elves.Elf;

import java.util.ArrayList;

public class ChildUpdates {
    private final int id;
    private final Double niceScore;
    private final ArrayList<String> giftsPreferences;
    private final Elf newElf;

    /**
     * constructor for a child updates object
     * @param id of the child wanted to be updated
     * @param niceScore the newNiceScore for the list
     * @param giftsPreferences the new preferences
     * @param elf the new elf of the child
     */
    public ChildUpdates(final int id,
                        final Double niceScore,
                        final ArrayList<String> giftsPreferences,
                        final Elf elf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.newElf = elf;
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

    public Elf getNewElf() {
        return newElf;
    }

    @Override
    public String toString() {
        return "ChildUpdates{" +
                "id=" + id +
                ", niceScore=" + niceScore +
                ", giftsPreferences=" + giftsPreferences +
                ", newElf=" + newElf +
                '}';
    }
}
