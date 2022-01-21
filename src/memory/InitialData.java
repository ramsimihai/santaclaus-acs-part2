package memory;

import children.Child;
import gifts.Gift;

import java.util.List;

public class InitialData {
    private final List<Child> childrenList;
    private final List<Gift> giftsList;

    /**
     * default constructor for an InitialData object
     */
    public InitialData() {
        childrenList = null;
        giftsList = null;
    }

    /**
     * constructor for an InitialData object
     * @param childrenList of first added children
     * @param giftsList of first added gifts
     */
    public InitialData(final List<Child> childrenList,
                       final List<Gift> giftsList) {
        this.childrenList = childrenList;
        this.giftsList = giftsList;
    }

    /**
     * getter of initial children's list
     */
    public List<Child> getChildrenList() {
        return childrenList;
    }

    /**
     * getter of initial gifts' list
     */
    public List<Gift> getGiftsList() {
        return giftsList;
    }

    /**
     * toString method for printing purposes
     */
    @Override
    public String toString() {
        return "{"
                + "childrenList=" + childrenList
                + ", giftsList=" + giftsList
                + '}';
    }
}
