package moderjavainaction.c3LambdaExpressions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/***
 * Putting lambdas and method references into practice
 * We'll continue with our initial problem of sorting a list of Apples with different ordering strategies. And we'll
 * show how we can progressively evolve a native solution into a concise solution, using all the concepts and features:
 *      behavior parameterization,
 *      anonymous classes,
 *      lambda expressions and
 *      method references.
 *
 *      inventory.sort(comparing(Apple::getWeight));
 */
public class P2LambdaAndMRPractice {

    static List<Apple> inventory = new ArrayList<>();

    public static void main(String[] args) {
        /*
        Step 1: Pass code
        Java 8 API provides us with a sort() method available on List, so we don't have to implement it. The sort() method
        has the following signature:
            void sort(Comparator<? super E> c)
        It expects a Comparator object as argument to compare two objects. This is how you can pass different strategies
        in Java: they have to be wrapped in an object. We say that the behavior of sort is parameterized.
        The first solution looks like this (see AppleComparator class).
         */
        inventory.sort(new AppleComparator());

        /*
        Step 2: Use an anonymous class
        Rather than implementing Comparator for the purpose of instantiating it once, you saw that you could use an
        anonymous class to improve your solution:
         */
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        /*
        Step 3: Use lambda expressions.
        Lambda expressions provide a lightweight syntax to achieve the same goal: passing code. In our case, the
        Comparator represents a function descriptor (T, T) -> int. Using lambdas:
         */
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        /*
        You can make your code more readable. Comparator includes a static helper method called comparing that takes a
        Function extracting a Comparable key and produces a Comparator object. It can be used as follows:
         */
        inventory.sort(Comparator.comparing(apple -> apple.getWeight()));

        /*
        Step 4: Use method reference
        You can use a method reference to make the code slightly less verbose:
         */
        inventory.sort(Comparator.comparing(Apple::getWeight));
    }


}
