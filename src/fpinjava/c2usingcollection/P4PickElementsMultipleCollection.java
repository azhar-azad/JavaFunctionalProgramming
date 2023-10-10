package fpinjava.c2usingcollection;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/***
 * Reusing Lambda Expressions
 */
public class P4PickElementsMultipleCollection {


    public static void main(String[] args) {

        /**
         * Suppose we have a few collections of names: friends, editors, comrades and so on.
         * We want to filter out names that start with a certain letter.
         */
        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        final List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");
        final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");

        /*
        Let's first take a naive approach to this using the filter() method.
         */
        final long countFriendsStartN = friends.stream()
                .filter(name -> name.startsWith("N")).count();
        final long countEditorsStartN = editors.stream()
                .filter(name -> name.startsWith("N")).count();
        final long countComradesStartN = comrades.stream()
                .filter(name -> name.startsWith("N")).count();

        System.out.println("Friends count with N: " + countFriendsStartN);
        System.out.println("Editors count with N: " + countEditorsStartN);
        System.out.println("Comrades count with N: " + countComradesStartN);

        /*
        Each of those lambda expressions are same; hence, the duplication.
        It's possible to store the lambda expression in an explicit reference of type Predicate
        and then pass it to the function; this is an easy way to remove the duplication.
         */
        final Predicate<String> startsWithN = name -> name.startsWith("N");

        final long countFriendsStartN2 = friends.stream().filter(startsWithN).count();
        final long countEditorsStartN2 = editors.stream().filter(startsWithN).count();
        final long countComradesStartN2 = comrades.stream().filter(startsWithN).count();

        System.out.println("Friends count with N: " + countFriendsStartN2);
        System.out.println("Editors count with N: " + countEditorsStartN2);
        System.out.println("Comrades count with N: " + countComradesStartN2);

        /**
         * The new variable gently removed the duplication that sneaked in. Unfortunately, it's about to
         * sneak back in with a vengeance, as we'll see next on the PickDifferentNames class.
         */
    }
}
