package elves;

public class ElvesFactory {
    public Elf createElf(String name) {
        switch (name) {
            case "white": return new WhiteElf(name);
            case "black": return new BlackElf(name);
            case "yellow": return new YellowElf(name);
            case "pink": return new PinkElf(name);
        }
        throw new IllegalArgumentException("The elf type " + name + " is not recognized.");
    }
}
