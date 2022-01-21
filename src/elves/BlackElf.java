package elves;

import static common.Constants.ONE_HUNDRED;
import static common.Constants.THIRTY;

public class BlackElf extends Elf {
    public BlackElf(final String name) {
        super(name);
    }

    /**
     * the black's elf skill is to ruin kiddos days
     */
    @Override
    public Double execute() {
        budget = budget - budget * THIRTY / ONE_HUNDRED;
        return budget;
    }
}
