package delivery;

public class DeliveryStrategyFactory {
    /**
     * factory's method to create instances of every type of delivery
     * @param name
     * @return
     */
    public DeliveryStrategy createDeliveryStrategy(final String name) {
        switch (name) {
            case "id": return new DeliveryByID(name);
            case "niceScore": return new DeliveryByNiceScore(name);
            case "niceScoreCity": return new DeliveryByNiceScoreCity(name);
        }
        throw new IllegalArgumentException("The delivery strategy type "
                + name + " is not recognized.");
    }
}
