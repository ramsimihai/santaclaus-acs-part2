package fileio;

import java.util.ArrayList;
import java.util.List;

public class ChangeOfTheYearInput {
    /** santaBudget every year */
    private final Double newSantaBudget;
    /** a list of newGifts that the santa can deliver */
    private final List<GiftInput> newGifts;
    /** a list of new children for delivering gifts to */
    private final List<ChildrenInput> newChildren;
    /** a list of new updates to specified children */
    private final List<ChildrenUpdatesInput> newUpdates;
    /** type of strategy of delivering gifts */
    private final String strategy;

    /**
     * constructor for an annual change
     */
    public ChangeOfTheYearInput(final Double newSantaBudget,
                                final ArrayList<GiftInput> newGifts,
                                final ArrayList<ChildrenInput> newChildren,
                                final ArrayList<ChildrenUpdatesInput> newUpdates,
                                final String strategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.newUpdates = newUpdates;
        this.strategy = strategy;
    }

    public final Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public final List<ChildrenInput> getNewChildren() {
        return newChildren;
    }

    public final List<ChildrenUpdatesInput> getNewUpdates() {
        return newUpdates;
    }

    public final List<GiftInput> getNewGifts() {
        return newGifts;
    }

    public final String getStrategy() {
        return strategy;
    }

    @Override
    public final String toString() {
        return "{"
                + "newSantaBudget=" + newSantaBudget
                + ", newGifts=" + newGifts
                + ", newChildren=" + newChildren
                + ", newUpdates=" + newUpdates
                + '}';
    }
}
