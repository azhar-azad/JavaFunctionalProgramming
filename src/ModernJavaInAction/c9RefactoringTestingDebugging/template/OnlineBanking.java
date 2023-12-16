package ModernJavaInAction.c9RefactoringTestingDebugging.template;

public abstract class OnlineBanking {

    public abstract void makeCustomerHappy(Customer c);

    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }
}
