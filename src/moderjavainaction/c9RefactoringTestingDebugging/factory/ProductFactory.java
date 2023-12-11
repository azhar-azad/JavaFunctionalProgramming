package moderjavainaction.c9RefactoringTestingDebugging.factory;

public class ProductFactory {

    /*
    Here, Loan, Stock, and Bond are subtypes of Product.
     */
    public static Product createProduct(String name) {
        switch (name) {
            case "loan": return new Loan();
            case "stock": return new Stock();
            case "bond": return new Bond();
            default: throw new RuntimeException("No such product " + name);
        }
    }
}
