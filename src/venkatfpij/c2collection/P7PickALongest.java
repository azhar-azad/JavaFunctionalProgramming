package venkatfpij.c2collection;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/***
 * Reducing a Collection to a Single Value,
 *
 * Comparing elements against each other or
 * carrying over computations from one element to the next.
 */
public class P7PickALongest {

    public static void main(String[] args) {

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        /*
        Let's read over the values in the friends collection of names and
        determine the total number of characters.
        We are leveraging the mapToInt() method, a variation of the map operation (variations like mapToInt(),
        mapToDouble(), and so on create a type-specialized streams such as IntStream and DoubleStream) and then
        reduced the resulting length to the sum value.
        Instead of using the sum() method, we could use a variety of methods like
        max() to find the longest length,
        min() to find the shortest length,
        sorted() to sort the lengths,
        average() to find the average of the length, and so on.
         */
        System.out.println("Total number of characters in all names: " + friends.stream()
                .mapToInt(name -> name.length())
                .sum());

        /*
        Let's read over the given collection of names and display the longest one. If there is more than one name
        with the same longest length, we'll display the first one we find.
        We can use the reduce() method to compare two elements against each other and pass along the result for
        further comparison with the remaining elements in the collection.
        The lambda expression conforms to the interface of an apply() method of a JDK functional interface named
        BinaryOperator. This is the type of parameter the reduce() method receives.
        How reduce() is working on our example:
        At first the first two elements are checked by the lambda expression and the result is used for subsequent call.
        In the second call, name1 is bound to the result of from the previous call, and name2 is bound to the third
        element in the collection. The result from the final call is returned as the result of the reduce() method call.
        The result of the reduce() method is an Optional. 
         */
        final Optional<String> aLongName = friends.stream()
                .reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2);
        aLongName.ifPresent(name -> System.out.printf("A longest name: %s", name));

        /*
        If we want to set a default or a base value, we can pass that value as an extra parameter to an overloaded
        variation of the reduce() method.
        For example, if the shortest name we want to pick is Steve, we can pass that to the reduce() method, like
        following. If any name was longer than the given base, it would get picked up; otherwise the function
        would return the base value.
        This version of reduce() does not return an Optional since if the collection is empty, the default will be
        returned; there's no concern of an absent or nonexistent value.
         */
        final String steveOrLonger = friends.stream()
                .reduce("Steve", (name1, name2) -> name1.length() >= name2.length() ? name1 : name2);
    }
}
