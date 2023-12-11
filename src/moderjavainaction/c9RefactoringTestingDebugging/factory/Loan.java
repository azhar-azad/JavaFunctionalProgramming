package moderjavainaction.c9RefactoringTestingDebugging.factory;

public class Loan extends Product {

    public Loan() {}
    public Loan(Integer initialPrice, Integer discount, String productClass) {
        super(initialPrice, discount, productClass);
    }
}
