package moderjavainaction.c9RefactoringTestingDebugging.factory;

public class Stock extends Product {
    public Stock() {}
    public Stock(Integer initialPrice, Integer discount, String productClass) {
        super(initialPrice, discount, productClass);
    }
}
