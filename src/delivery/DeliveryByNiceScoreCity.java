package delivery;

import children.Child;
import workshop.Santa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryByNiceScoreCity implements DeliveryStrategy {
    private String name;

    public DeliveryByNiceScoreCity(final String name) {
        this.name = name;
    }

    /**
     * set the list of children in order of the nice Score of the city from
     * which every child is, then sorts it in order of city's name, then children's id
     */
    @Override
    public void delivery() {
        Santa santa = Santa.getInstance();
        santa.sortChildren();

        // adds nice score of all the cities in the simulation
        santa.addsNiceScoreCities();
        // calculates the nice score of the city for every child
        santa.calculatesNiceScoreCityChild();

        // sorts the list of children by comparators
        List<Child> sortedChildren = santa.getChildren();
        Comparator<Child> niceScoreCityComparator = Comparator.comparing(Child::getNiceScoreCity)
                                                              .reversed()
                                                              .thenComparing(Child::getCity)
                                                              .thenComparing(Child::getId);
        sortedChildren = sortedChildren.stream()
                                       .sorted(niceScoreCityComparator)
                                        .collect(Collectors.toList());

        // sets the children list for the year delivery & starts the delivery
        santa.setChildren(sortedChildren);
        santa.yearDelivery();
    }
}
