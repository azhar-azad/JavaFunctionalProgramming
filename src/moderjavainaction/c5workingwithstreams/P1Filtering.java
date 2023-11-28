package moderjavainaction.c5workingwithstreams;

import utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Stream interface supports a filter() method that takes a predicate as argument and returns a stream including
 * all elements that match the predicate.
 */
public class P1Filtering {

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
        Filtering with a predicate
        Creating a vegetarian menu...
         */
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        Utils.printList(vegetarianMenu);

        /*
        Filtering unique elements
        Streams support a method called distinct() that returns a stream with unique elements (according to the
        implementation of the hashcode() and equals() methods of the objects produced by the stream).
        For example, the following code filters all even numbers from a list and then eliminates duplicates.
         */
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        List<Integer> uniqueNumbers = numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(Collectors.toList());
        Utils.printListSingleLine(uniqueNumbers);

    }
}
