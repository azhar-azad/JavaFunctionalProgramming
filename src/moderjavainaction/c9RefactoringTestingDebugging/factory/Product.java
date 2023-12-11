package moderjavainaction.c9RefactoringTestingDebugging.factory;

public class Product {

    private Integer initialPrice;
    private Integer discount;
    private String productClass;

    public Product() {}

    public Product(Integer initialPrice, Integer discount, String productClass) {
        this.initialPrice = initialPrice;
        this.discount = discount;
        this.productClass = productClass;
    }

    @Override
    public String toString() {
        return "initialPrice = " + initialPrice
                + "\ndiscount = " + discount
                + "\nproductClass = " + productClass;
    }
}
