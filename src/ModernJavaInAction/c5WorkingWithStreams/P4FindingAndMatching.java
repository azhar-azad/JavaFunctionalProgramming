package ModernJavaInAction.c5WorkingWithStreams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/***
 * Finding and matching
 * A common data processing idiom is finding whether some elements in a set of data match a given property.
 * The Streams API provides such facilities through the allMatch(), anyMatch(), noneMatch(), findFirst(), and
 * findAny() methods of a stream.
 */
public class P4FindingAndMatching {

    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("seasonal fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );

        /*
        Checking to see if a predicate matches at least one element
        The anyMatch() method can be used to answer the question "Is there an element in the stream matching the
        given predicate?"
        For example, you can use it to find out whether the menu has a vegetarian option.
        The anyMatch() method returns a boolean and is therefore a terminal operation.
         */
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        /*
        Checking to see if a predicate matches all elements
        The allMatch() method works similarly to anyMatch() but will check to see if all the elements of the stream
        match the given predicate.
        For example, you can use it to find out whether the menu is healthy (all dishes are below 1000 calories).
         */
        boolean isHealthy = menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);
        System.out.println("Is the menu healthy? " + isHealthy);

        /*
        noneMatch(): The opposite of allMatch() is noneMatch(). It ensures that no elements in the stream match the
        given predicate.
        For example, you could rewrite the previous example as follows using noneMatch.
         */
        boolean isHealthy2 = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        System.out.println("Is the menu healthy? " + isHealthy2);

        /*
        These three operations - anyMatch(), allMatch(), and noneMatch() - make use of what we call short-circuiting,
        a stream version of the familiar Java short-circuiting && and || operators.
         */

        /*
        Finding an element
        The findAny() method returns an arbitrary element of the current stream.
        For example, you may wish to find a dish that's vegetarian. You can combine the filter() method and findAny()
        method to express this query.
         */
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        /*
        The Optional<T> class (java.util.Optional) is a container class to represent the existence or absence of a
        value. There are a few methods available in Optional that force you to explicitly check for the presence of
        a value or deal with the absence of a value:
            - isPresent() returns true if Optional contains a value, false otherwise.
            - ifPresent(Consumer<T> block) executes the given block if a value is present.
            - T get() returns the value if present; otherwise it throws a NoSuchElementException.
            - T orElse(T other) returns the value if present; otherwise it returns a default value.
        For example, in the previous code you'd need to explicitly check for the presence of a dish in the Optional
        object to access its name:
         */
        menu.stream().filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        /*
        Finding the first element
        Some streams have an encounter order that specifies the order in which items logically appear in the stream
        (for example, a stream generated from a List or from a sorted sequence of data). For such streams you may wish
        to find the first element. There's the findFirst() method for this, which works similarly to findAny().
         */
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .findFirst(); // 9
    }
}
