package moderjavainaction.c9RefactoringTestingDebugging;

import moderjavainaction.c9RefactoringTestingDebugging.factory.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/***
 * Factory Pattern
 * The factory design pattern lets you create objects without exposing the instantiation logic to the client.
 */
public class P5FactoryPattern {

    public static void main(String[] args) {

        /*
        Suppose that you're working for a bank that needs a way of creating different financial products: loans, bonds,
        stocks, and so on. Typically, you'd create a Factory class with a method that's responsible for the creation
        of different objects.
        See the ProductFactory class.
         */
        /*
        The createProduct() method could have additional logic to configure each created product. But the benefit is
        that you can create these objects without exposing the constructor and the configuration to the client, which
        makes the creation of products simpler for the client.
         */
        Product p = ProductFactory.createProduct("loan");
        System.out.println(p);

        /*
        Using Lambda Expressions
        You can refer to constructors the way that you refer to methods: by using method references. Here's how to refer
        to the Loan constructor.
         */
        Supplier<Product> loanSupplier = Loan::new;
        Product loan = loanSupplier.get();
        System.out.println(loan);
        /*
        Using this technique, you could rewrite the preceding code by creating a Map that maps a product name to its
        constructor.
        See ProductFactoryLambda class.
         */
        /*
        You can use this Map to instantiate different products, as you did with the factory design pattern.
         */
        Product p2 = ProductFactoryLambda.createProduct("bond");
        System.out.println(p2);
        /*
        This technique is neat. But this technique doesn't scale well if the factory method createProduct() needs to
        take multiple arguments to pass to the product constructors. You'd have to provide a function interface other
        than a simple Supplier.
         */
        /*
        Suppose that you want to refer to constructors for products that take three arguments (two Integers and a
        String); you need to create a special functional interface TriFunction to support such constructors. As a
        result, the signature of the Map becomes more complex.
        See TriFunction interface and ProductFactoryLambdaComplex class.
         */
        Product p3 = ProductFactoryLambdaComplex.createProduct("stock", 100, 10, "High");
        System.out.println(p3);
        // initialPrice = 100
        // discount = 10
        // productClass = High


    }
}
