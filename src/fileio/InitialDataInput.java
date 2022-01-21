package fileio;

import java.util.ArrayList;
import java.util.List;

public class InitialDataInput {
    /** list of initial children added to the delivery */
    private final List<ChildrenInput> childrenList;
    /** list of initial gifts that can be delivered by santa */
    private final List<GiftInput> giftsList;

    /**
     * constructor for initial data object
     */
    public InitialDataInput(final ArrayList<ChildrenInput> childrenList,
                            final ArrayList<GiftInput> giftsList) {
        this.childrenList = childrenList;
        this.giftsList = giftsList;
    }

    public final List<ChildrenInput> getChildrenList() {
        return childrenList;
    }

    public final List<GiftInput> getGiftsList() {
        return giftsList;
    }

    @Override
    public final String toString() {
        return "{"
                + "childrenList=" + childrenList
                + ", giftsList=" + giftsList
                + '}';
    }
}
