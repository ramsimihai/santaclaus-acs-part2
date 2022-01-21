package delivery;

import children.Child;
import workshop.Santa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryByNiceScoreCity implements DeliveryStrategy {
    private String name;

    public DeliveryByNiceScoreCity(String name) {
        this.name = name;
    }

    @Override
    public void delivery() {
        Santa santa = Santa.getInstance();
        santa.sortChildren();
        santa.addsNiceScoreCities();
        santa.calculatesNiceScoreCityChild();

        List<Child> sortedChildren = santa.getChildren();
        Comparator<Child> niceScoreCityComparator = Comparator.comparing(Child::getNiceScoreCity)
                                                              .reversed()
                                                              .thenComparing(Child::getCity)
                                                              .thenComparing(Child::getId);
        sortedChildren = sortedChildren.stream().sorted(niceScoreCityComparator).collect(Collectors.toList());

        santa.setChildren(sortedChildren);
        santa.yearDelivery();
    }
}
