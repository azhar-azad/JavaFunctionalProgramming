package fpinjava.c2usingcollection;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/***
 * Using Lexical Scoping and Closures
 */
public class P5PickDifferentNames {

    public static void main(String[] args) {

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        /*
        Let's pick the names that start with N or B from the friends collection of names.
        Continuing with the previous example, we may be tempted to write something like the following:
         */
        final Predicate<String> startsWithN = name -> name.startsWith("N");
        final Predicate<String> startsWithB = name -> name.startsWith("B");

        final long countFriendsStartN = friends.stream().filter(startsWithN).count();
        final long countFriendsStartB = friends.stream().filter(startsWithB).count();

        System.out.println("Friends count with N: " + countFriendsStartN);
        System.out.println("Friends count with B: " + countFriendsStartB);

        /*
        Seems reasonable, but the two predicates are mere duplicates, with only the letter they use being different.
        Let's remove the duplication with Lexical Scoping.
        Show the function checkIfStartsWith(String) and read the description.
        We can use the lambda expression returned by checkIfStartsWith() in the call to the filter() method.
         */
        final long countFriendsStartN2 = friends.stream().filter(checkIfStartsWith("N")).count();
        final long countFriendsStartB2 = friends.stream().filter(checkIfStartsWith("B")).count();

        System.out.println("Friends count with N: " + countFriendsStartN2);
        System.out.println("Friends count with B: " + countFriendsStartB2);

        /*
        Refactoring to Narrow the Scope
        Instead of using a static method, it would be nice to narrow the function's scope to where it's needed.
        We can do that using a Function interface.
         */
        final Function<String, Predicate<String>> startsWithLetter = (String letter) -> {
            Predicate<String> checkStarts = (String name) -> name.startsWith(letter);
            return checkStarts;
        };

        final long countFriendsStartN3 = friends.stream().filter(startsWithLetter.apply("N")).count();
        final long countFriendsStartB3 = friends.stream().filter(startsWithLetter.apply("B")).count();

        System.out.println("Friends count with N: " + countFriendsStartN3);
        System.out.println("Friends count with B: " + countFriendsStartB3);

        // We can take the conciseness up another notch by removing the types and letting Java compiler infer the
        // types based on the context.
        final Function<String, Predicate<String>> startsWithLetter2 = letter -> name -> name.startsWith(letter);

        final long countFriendsStartN4 = friends.stream().filter(startsWithLetter2.apply("N")).count();
        final long countFriendsStartB4 = friends.stream().filter(startsWithLetter2.apply("B")).count();

        System.out.println("Friends count with N: " + countFriendsStartN4);
        System.out.println("Friends count with B: " + countFriendsStartB4);

        /**
         * Function vs Predicate
         * A Predicate<T> takes in one parameter of type T and returns a boolean result to indicate a decision
         * for whatever check it represents. Methods like filter() that evaluate candidate elements take in a
         * Predicate as their parameters.
         * A Function<T, R> represents a function that takes a parameter of type T and returns a result of type R.
         * This is more general than a Predicate that always returns a boolean. We can use a Function anywhere we
         * want to transform an input to another value, so its quite logical that the map() method uses Function
         * as its parameter.
         */

    }

    /**
     * As a first option, we could extract the letter as a parameter to a function and pass the function as an
     * argument to the filter() method. That's a reasonable idea, but the filter() method will not accept arbitrary
     * function. It insists on receiving a function that accepts one parameter representing the context element in
     * the collection, and returning a boolean result. It's expecting a Predicate.
     *
     * @param letter We need a variable that will cache the letter for later use, and hold onto it until the
     *               parameter, name is received.
     * @return Will return a Predicate(boolean) indicating that the param letter is the first letter on name or not.
     */
    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }
}
