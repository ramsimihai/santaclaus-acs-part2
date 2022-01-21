package fileio;

public class GiftInput {
    /** product name of a gift */
    private final String productName;
    /** price of a gift */
    private final Double price;
    /** the category of the gift */
    private final String category;
    /** the maximum amount of the specified gift that can be delivered */
    private final int quantity;

    /**
     * constructor for a gift collected from input
     */
    public GiftInput(final String productName,
                     final double price,
                     final String category,
                     final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public final String getProductName() {
        return productName;
    }

    public final Double getPrice() {
        return price;
    }

    public final String getCategory() {
        return category;
    }

    public final int getQuantity() {
        return quantity;
    }

    @Override
    public final String toString() {
        return "{"
                + "productName='" + productName + '\''
                + ", price=" + price
                + ", category='" + category + '\''
                + '}';
    }
}
