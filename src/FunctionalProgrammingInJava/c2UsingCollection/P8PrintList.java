package FunctionalProgrammingInJava.c2UsingCollection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Joining Elements
 */
public class P8PrintList {

    public static void main(String[] args) {

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        /*
        To print the list of names of the friends list, we have to iterate through the list and print each element.
         */
        for (String name: friends) {
            System.out.print(name + ", ");
        }
        System.out.println();

        /*
        Now there's a comma at the end and unfortunately, the loop will run its course and there's no easy way to
        tell the last element apart from the rest. To fix this, we can fall back on the habitual loop.
         */
        for (int i = 0; i < friends.size() - 1; i++) {
            System.out.print(friends.get(i) + ", ");
        }
        if (friends.size() > 0)
            System.out.println(friends.get(friends.size() - 1));

        /*
        To escape from this, we can use a StringJoiner class. A StringJoiner class cleans up all that mess in Java 8
        and the String class has an added convenience method join() to solve problems like this.
         */
        System.out.println(String.join(", ", friends));

        /*
        We can also transform the elements before joining them.
         */
        System.out.println(friends.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(", ")));
    }
}
