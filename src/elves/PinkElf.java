package elves;

import static common.Constants.ONE_HUNDRED;
import static common.Constants.THIRTY;

public class PinkElf extends Elf {
    public PinkElf(final String name) {
        super(name);
    }

    /**
     * pink elf's skill is to enjoy kiddos on christmas day
     */
    @Override
    public Double execute() {
        budget = budget + budget * THIRTY / ONE_HUNDRED;
        return budget;
    }
}
