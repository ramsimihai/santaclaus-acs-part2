package elves;

import children.Child;
import gifts.Gift;
import workshop.Santa;

import java.util.List;

public class YellowElf extends Elf {
    private Child unluckyKiddo;

    public YellowElf(String name) {
        super(name);
    }

    public final void setUnluckyKiddo(final Child unluckyKiddo) {
        this.unluckyKiddo = unluckyKiddo;
    }

    /**
     * yellow elf is a cutie and gives an unlucky kiddo a gift
     * @return
     */
    @Override
    public Double execute() {
        if (unluckyKiddo == null) {
            return 0.0;
        }

        // checks if the given child didnt get any gifts
        if (unluckyKiddo.getReceivedGifts().size() == 0) {
            String preferredCategory = unluckyKiddo.getGiftsPreferences().get(0);
            List<Gift> availableGifts = Santa.getInstance().getAvailableGifts();

            // iterates through gifts
            int i = 0;
            for (Gift singleGift : availableGifts) {
                int firstGift = 0;
                // if the gift is from the preffered category of the child, it checks if
                // the lowest priced gift in the database could be given to this child
                if (singleGift.getCategory().equals(preferredCategory)) {
                    firstGift++;
                    if (singleGift.getQuantity() != 0) {
                        unluckyKiddo.getReceivedGifts().add(singleGift);
                        availableGifts.get(i).setQuantity(availableGifts.get(i).getQuantity() - 1);
                        i++;
                        return 0.0;
                    }

                    // only the first gift from the preffered category is checked
                    if (firstGift == 1) {
                        return 0.0;
                    }
                }
                i++;
            }
        }


        return 0.0;
    }
}
