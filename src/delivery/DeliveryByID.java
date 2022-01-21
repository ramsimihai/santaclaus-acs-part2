package delivery;

import workshop.Santa;

public class DeliveryByID implements DeliveryStrategy {
    private String name;

    public DeliveryByID(final String name) {
        this.name = name;
    }

    /**
     * set the list of children in the state sorted by their id
     */
    @Override
    public void delivery() {
        Santa santa = Santa.getInstance();
        santa.yearDelivery();
    }
}
