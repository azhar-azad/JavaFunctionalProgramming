package moderjavainaction.c9RefactoringTestingDebugging.factory;

public class Bond extends Product {
    public Bond() {}

    public Bond(Integer initialPrice, Integer discount, String productClass) {
        super(initialPrice, discount, productClass);
    }
}
