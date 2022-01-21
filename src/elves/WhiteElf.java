package elves;

public class WhiteElf extends Elf {
    public WhiteElf(final String name) {
        super(name);
    }

    @Override
    public Double execute() {
        return budget;
    }
}
