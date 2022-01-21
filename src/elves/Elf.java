package elves;

public abstract class Elf {
    protected String name;
    protected Double budget;

    public Elf() {
    }

    public Elf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public abstract Double execute();

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                '}';
    }
}
