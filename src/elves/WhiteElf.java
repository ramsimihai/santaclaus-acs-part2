package elves;

public class WhiteElf extends Elf {
    public WhiteElf(final String name) {
        super(name);
    }

    /**
     * white elf's skill is to do nothing. lazy as#
     */
    @Override
    public Double execute() {
        return budget;
    }
}
