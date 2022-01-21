package elves;

public abstract class Elf {
    protected String name;
    protected Double budget;

    public Elf() {
    }

    public Elf(String name) {
        this.name = name;
    }

    public final void setBudget(final Double budget) {
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    /**
     * executes a functionality of the specified elf
     * @return
     */
    public abstract Double execute();
}
