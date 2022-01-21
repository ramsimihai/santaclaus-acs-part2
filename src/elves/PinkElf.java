package elves;

public class PinkElf extends Elf {
    public PinkElf(final String name) {
        super(name);
    }

    @Override
    public Double execute() {
        return budget = budget + budget * 30 / 100;
    }
}
