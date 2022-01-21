package elves;

public class PinkElf extends Elf {
    public PinkElf(String name) {
        super(name);
    }

    /**
     * pink elf's skill is to enjoy kiddos on christmas day
     */
    @Override
    public Double execute() {
        return budget = budget + budget * 30 / 100;
    }
}
