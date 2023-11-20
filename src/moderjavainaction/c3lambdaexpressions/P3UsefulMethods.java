package moderjavainaction.c3lambdaexpressions;

import moderjavainaction.c2behaviorparameterization.Color;
import moderjavainaction.c2behaviorparameterization.P1BehaviorParameterization;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/***
 * Useful methods to compose lambda expressions
 * Many functional interfaces such as Comparator, Function, and Predicate that are used to pass lambda expressions
 * provide methods that allow composition. In practice, it means you can combine several simple lambda expressions to
 * build more complicated ones.
 * For example, you can combine two predicates into a larger predicate that performs an or operation between the two
 * predicates.
 * These methods are the default functions of their associated interfaces.
 */
public class P3UsefulMethods {

    public static void main(String[] args) {

        List<Apple> inventory = new ArrayList<>();

        /*
        Composing Comparators
        Here is a lambda expression to sort Apples based on their weight:
            Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        */

        /*
        Reversed Order
        What if you wanted to sort the apples by decreasing weight? There's no need to create a different instance of a
        Comparator. The interface includes a default method named reversed() that reverses the ordering of a given
        comparator.
         */
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        /*
        Chaining Comparators
        What if there are two apples that have the same weight? Which apple should have priority in the sorted list?
        You may provide a second comparator to further refine the comparison.
         */
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getCountry));

        /*
        * Composing Predicates
        The Predicate interface includes three methods that let you reuse an existing Predicate to create more
        complicated ones: negate(), and(), and or().
         */

        /*
        negate(): You can use the method negate() to return the negation of a Predicate, such as an apple that is not
        red.
         */
        Predicate<Apple> redApple = (Apple apple) -> Color.RED.equals(apple.getColor());
        Predicate<Apple> notRedApple = redApple.negate();

        /*
        and(): You may want to combine two lambdas to say that an apple is both red and heavy with the and() method.
         */
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);

        /*
        or(): You can combine the resulting predicate one step further to express apples that are red and heavy or
        only green apples.
         */
        Predicate<Apple> readAndHeavyAppleOrGreen =
                redApple
                        .and(apple -> apple.getWeight() > 150)
                        .or(apple -> Color.GREEN.equals(apple.getColor()));

        /*
        Composing Functions
        You can also compose lambda expressions represented by the Function interface. The Function interface comes
        with two default methods for this, andThen() and compose(), which both return an instance of Function.
         */

        /*
        andThen(): Returns a function that first applies a given function to an input and then applies another function
        to the result of that application. For example, given a function f that increments a number (x -> x + 1) and
        another function g that multiples a number by 2, you can combine them to create a function h that first
        increments a number and then multiplies the result by 2:
         */
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g); // g(f(x))
        int result = h.apply(1); // will return 4

        /*
        compose(): You can also use the method compose() similarly to first apply the function given as argument to
        compose and then apply the function to the result. For example, in the previous example using compose(), it
        would mean f(g(x)) instead of g(f(x)) using andThen:
         */
        Function<Integer, Integer> h2 = f.compose(g); // f(g(x))
        int result2 = h2.apply(1); // will return 3
    }
}
