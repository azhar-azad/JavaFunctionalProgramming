package fpinjava.c2UsingCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P2Transform {

    public static void main(String[] args) {

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        /***
         * Suppose we're asked to convert a list of names to all capital letters.
         */

        /*
        Let's start by creating a new collection of uppercase names from the given collection and
        then populate it with all-uppercase names, one element at a time, while iterating through
        the original list.
         */
        final List<String> uppercaseNames1 = new ArrayList<>();
        for (String name: friends) {
            uppercaseNames1.add(name.toUpperCase());
        }

        /*
        As a first step to move toward a functional style, we could use the internal iterator forEach() method,
        to replace the for loop.
        We are using the internal iterator, but that still required the empty list and the effort to add
        elements to it.
         */
        final List<String> uppercaseNames2 = new ArrayList<>();
        friends.forEach(name -> uppercaseNames2.add(name.toUpperCase()));
        System.out.println(uppercaseNames2);

        /*
        A Stream is much like an iterator on a collection of objects and provides some nice fluent functions.
        The Stream's map() method can map or transform a sequence of input to a sequence of output - that fits
        quite well for the task at hand.

        However, element types in the input don't have to match the element types in the output collection.
        We could have passed to the map() method a block of code that returned, for example, the number of
        characters in a given name.
         */
        friends.stream()
                .map(name -> name.toUpperCase())
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        friends.stream()
                .map(name -> name.length())
                .forEach(count -> System.out.print(count + " "));

        /*
        Let's use method reference to be just a bit concise.
         */
        friends.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
