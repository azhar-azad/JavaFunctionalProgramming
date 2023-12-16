package ModernJavaInAction.c9RefactoringTestingDebugging;

import ModernJavaInAction.c9RefactoringTestingDebugging.template.Customer;
import ModernJavaInAction.c9RefactoringTestingDebugging.template.OnlineBankingLambda;

/***
 * Template Method Pattern
 * The template method design pattern is a common solution when you need to represent the outline of an algorithm and
 * have the additional flexibility to change certain parts of it. In other words, the template method pattern is useful
 * when you find yourself saying "I'd love to use this algorithm, but I need to change a few lines, so it does what I
 * want".
 */
public class P2TemplateMethodPattern {

    public static void main(String[] args) {

        /*
        Suppose that you need to write a simple online banking application. Users typically enter a customer ID; the
        application fetches the customer's details from the bank's database and does something to make the customer happy.
        Different online banking applications for different banking branches may have different ways of making a customer
        happy (such as adding a bonus to his account or sending him less paperwork). You can write the following
        abstract class to represent the online banking application.
        See OnlineBanking class.
         */
        /*
        The processCustomer() method provides a sketch for the online banking algorithm; Fetch the customer given its ID
        and make the customer happy. Now different branches can provide different implementations of the
        makeCustomerHappy() method by sub-classing the OnlineBanking class.
         */

        /*
        Using Lambda Expression
        You can tackle the same problem (creating an outline of an algorithm and letting implementers plug in some parts)
        by using lambdas. The component of the algorithms you want to plug in can be represented by lambda expressions
        or method references. Here, we introduce a second argument to the processCustomer method of type Consumer<Customer>
        because it matches the signature of the method makeCustomerHappy defined earlier.
        See OnlineBankingLambda class.
         */
        /*
        Now, you can plug in different behaviours directly without subclassing the OnlineBanking class by passing
        lambda expressions.
         */
        new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello " + c.getName()));

        /*
        This example shows how lambda expressions can help you remove the boilerplate inherent to design patterns.
         */
    }
}
