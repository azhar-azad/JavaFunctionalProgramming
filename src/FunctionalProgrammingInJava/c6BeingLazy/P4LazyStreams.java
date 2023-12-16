package FunctionalProgrammingInJava.c6BeingLazy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/***
 * Leveraging the Laziness of Streams
 * We've seen the facilities that the new Stream interface offers, but one of their
 * most salient features is they're really lazy, in a good way. Streams can
 * postpone not just one, but a sequence of evaluations so that only the most
 * essential parts of the logic are evaluated, and only when needed.
 */
public class P4LazyStreams {

    /*
    Intermediate and Terminal Operations
    Streams have two types of methods: intermediate and terminal, which work
    together.
    Methods like map() and filter() are intermediate; calls to them
    return immediately and the lambda expressions provided to them are not
    evaluated right away. The core behaviour of these methods is cached for later
    execution and no real work is done when they're called. The cached behaviour
    is run when one of the terminal operations, like findFirst() and reduce(), is
    called. Not all the cached code is executed, however, and the computation
    will complete as soon as the desired result is found.
     */

    /*
    Suppose we're given a collection of names and are asked to print in all
    caps the first name that is only three letters long. We can use Stream's
    functional-style methods to achieve this. But let's create a few helper
    methods. The two helper methods simply print the parameters they receive
    before returning the expected results.
     */
    private static int length(final String name) {
        System.out.println("getting length for " + name);
        return name.length();
    }
    private static String toUpper(final String name) {
        System.out.println("converting to uppercase: " + name);
        return name.toUpperCase();
    }

    //...

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Brad", "Kate", "Kim", "Jack", "Joe",
                "Mike", "Susan", "George", "Robert", "Julia", "Parker", "Benson");

        /*
        The operations are lazy because the filter() method does not plow through
        all the elements in the collection in one shot. Instead, it runs until it
        finds the first element that satisfies the condition given in the attached
        lambda expression. As soon as it finds an element, it passes that to the
        next method in the chain. This next method, map() in this example, does
        its part on the given input and passes it down the chain. When the
        evaluation reaches the end, the terminal operation cheeks to see if it has
        received the result it's looking for. If the terminal operation got what
        it needed, the computation of the chain terminates. If the terminal
        operation is not satisfied, it will ask for the chain of operations to be
        carried out for more elements in the collection.
         */
        final String firstNameWith3Letters = names.stream()
                .filter(name -> length(name) == 3)
                .map(name -> toUpper(name))
                .findFirst()
                .get();

        System.out.println(firstNameWith3Letters);

        /*
        Peeking int the Laziness
        To really see that the lazy evaluations didn't start until we reached the
        terminal operation, let's break the chain from the previous code into steps.
         */
        Stream<String> namesWith3Letters = names.stream()
                .filter(name -> length(name) == 3)
                .map(name -> toUpper(name));

        System.out.println("Stream created, filtered, mapped...");
        System.out.println("ready to call findFirst...");

        final String firstNameWith3Letters2 = namesWith3Letters.findFirst().get();

        System.out.println(firstNameWith3Letters2);

        /*
        From the output we can clearly see that the intermediate operations delayed
        their real work until the last responsible moment, when the terminal
        operation was invoked. And even then, they only did the minimum work
        necessary to satisfy the terminal operation. 
         */
    }
}
