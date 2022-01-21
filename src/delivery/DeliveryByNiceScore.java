package delivery;

import children.Child;
import workshop.Santa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryByNiceScore implements DeliveryStrategy {
    private String name;

    public DeliveryByNiceScore(final String name) {
        this.name = name;
    }

    /**
     * set the list of children in order of the nice Score of every child
     * then sorts it by the ID
     */
    @Override
    public void delivery() {
        Santa santa = Santa.getInstance();
        List<Child> sortedChildren = santa.getChildren();

        Comparator<Child> niceScoreComparator = Comparator.comparing(Child::getAverageScore)
                .reversed().thenComparing(Child::getId);
        sortedChildren = sortedChildren.stream().sorted(niceScoreComparator)
                .collect(Collectors.toList());

        santa.setChildren(sortedChildren);
        santa.yearDelivery();
    }
}
