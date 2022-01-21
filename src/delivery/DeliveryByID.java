package delivery;

import children.Child;
import gifts.Gift;
import workshop.Santa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryByID implements DeliveryStrategy {
    private String name;

    public DeliveryByID(String name) {
        this.name = name;
    }

    @Override
    public void delivery() {
        Santa santa = Santa.getInstance();
        santa.yearDelivery();
    }
}
