package fileio;

import java.util.ArrayList;

public final class ChildrenInput {
    /** id of a child to be recognised later */
    private final int id;
    /** last name of the child */
    private final String lastName;
    /** first name of the child */
    private final String firstName;
    /** age of the child */
    private final int age;
    /** city where a child lives (weirdos) */
    private final String city;
    /** initial niceScore of a child */
    private final Double niceScore;
    /** initial preferences of a child */
    private final ArrayList<String> giftsPreferences;
    /** extra niceScore per child */
    private final Double niceScoreBonus;
    /** child's own elf */
    private final String elf;

    /**
     * constructor for a children object to extract data
     */
    public ChildrenInput(final int id,
                         final String lastName,
                         final String firstName,
                         final int age,
                         final String city,
                         final Double niceScore,
                         final ArrayList<String> giftsPreferences,
                         final Double niceScoreBonus,
                         final String elf) {

        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreBonus = niceScoreBonus;
        this.elf = elf;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public String getElf() {
        return elf;
    }

    @Override
    public String toString() {
        return "ChildrenInput{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", niceScore=" + niceScore +
                ", giftsPreferences=" + giftsPreferences +
                ", niceScoreBonus=" + niceScoreBonus +
                ", elf='" + elf + '\'' +
                '}';
    }
}
