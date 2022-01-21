package elves;

import children.Child;

public class YellowElf extends Elf {
    private Child unluckyKiddo;

    public YellowElf(String name) {
        super(name);
    }

    public void setUnluckyKiddo(Child unluckyKiddo) {
        this.unluckyKiddo = unluckyKiddo;
    }

    @Override
    public Double execute() {
        return 0.0;
    }
}
