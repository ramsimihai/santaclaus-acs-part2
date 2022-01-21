package gifts;

public class Gift {
    private final String productName;
    private final Double price;
    private final String category;
    private int quantity;

    /**
     * constructor with parameters to create a Gift object
     * @param productName
     * @param price
     * @param category
     * @param quantity
     */
    public Gift(final String productName,
                 final double price,
                 final String category,
                 final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    /**
     * getter for productName
     * @return productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * getter for price
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * getter for category
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * getter for amount of available gifts
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) { this.quantity = quantity; }
    /**
     * toString() method for printing purposes
     * @return String
     */
    @Override
    public String toString() {
        return "{"
                + "productName='" + productName + '\''
                + ", price=" + price
                + ", category='" + category + '\''
                + '}';
    }
}
