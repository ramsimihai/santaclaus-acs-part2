package elves;

public class BlackElf extends Elf {
    public BlackElf(final String name) {
        super(name);
    }

    @Override
    public Double execute() {
       return budget = budget - budget * 30 / 100;
    }
}
