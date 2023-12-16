package FunctionalProgrammingInJava.c2UsingCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P3PickElements {

    public static void main(String[] args) {

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        /**
         * From a list of names, let's pick the ones that start with the letter N.
         */

        /*
        Let's first code it using the old approach.
         */
        final List<String> startsWithN = new ArrayList<>();
        for (String name: friends) {
            if (name.startsWith("N")) {
                startsWithN.add(name);
            }
        }

        /*
        Let's refactor the code to use the filter() method.
        The filter() method expects a lambda expression that returns a boolean result. If the lambda expression
        returns a true, the element in context while executing that lambda expression is added to a result collection;
        or skipped otherwise.
        Finally, the method returns a Stream with only elements for which the lambda expression yielded a true.
        In the end we transformed the result into a List using the collect() method.
         */
        final List<String> startsWithN2 = friends.stream()
                .filter(name -> name.startsWith("N"))
                .collect(Collectors.toList());
        System.out.printf("Found %d names%n", startsWithN2.size());

        /**
         * The conciseness we've achieved by using lambda expressions so far is nice, but code duplication may
         * sneak in quickly if we're not careful. Let's address the concern in PickElementsMultipleCollection class.
         */
    }
}
