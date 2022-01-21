package delivery;

public class DeliveryStrategyFactory {
    public DeliveryStrategy createDeliveryStrategy(String name) {
        switch (name) {
            case "id": return new DeliveryByID(name);
            case "niceScore": return new DeliveryByNiceScore(name);
            case "niceScoreCity": return new DeliveryByNiceScoreCity(name);
        }
        throw new IllegalArgumentException("The elf type " + name + " is not recognized.");
    }
}
