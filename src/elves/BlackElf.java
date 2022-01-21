package elves;

public class BlackElf extends Elf {
    public BlackElf(final String name) {
        super(name);
    }

    /**
     * the black's elf skill is to ruin kiddos days
     */
    @Override
    public Double execute() {
       return budget = budget - budget * 30 / 100;
    }
}
