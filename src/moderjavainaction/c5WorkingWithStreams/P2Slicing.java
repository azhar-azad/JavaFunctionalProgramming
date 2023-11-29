package moderjavainaction.c5WorkingWithStreams;

import utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 * There are operations available that let you selecting and skipping elements in different ways,
 * i.e. efficiently select or drop elements using a predicate, ignore the first
 * few elements of a stream, or truncate a stream to a given size, etc.
 */
public class P2Slicing {

    public static void main(String[] args) {

        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER)
        );

        /*
        Slicing using a predicate
        Java 9 added two new methods that are useful for efficiently selecting elements in a stream:
        takeWhile() and dropWhile().
         */

        /*
        takeWhile(): How would you select the dishes that have fewer than 320 calories? Instinctively, you know
        already that the operation filter() can be used to get the desired output. But, you'll notice that the
        initial list was already sorted on the number of calories!
        Instead of applying the predicate to the whole stream, we can use the takeWhile() method.
        It lets you slice any stream using a predicate. And it stops once it has found an element that fails to match.
         */
        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
        Utils.printList(slicedMenu1);

        /*
        dropWhile(): How about getting the other elements though? How about finding the elements that have greater than
        320 calories?
        The dropWhile() operation is the complement of takeWhile(). It throws away the elements at the start where the
        predicate is false. Once the predicate evaluates to true it stops and returns all the remaining elements, and it
        even works if there are an infinite number of remaining elements!
         */
        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
        Utils.printList(slicedMenu2);

        /*
        Truncating a stream
        limit(n): Returns another stream that's no longer than a given size. The following code creates a list by
        selecting the first three dishes that have more than 300 calories.
         */
        List<Dish> dishes1 = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        Utils.printList(dishes1);

        /*
        Skipping elements
        skip(n): Streams support the skip(n) method to return a stream that discards the first n elements. If the
        stream has fewer than n elements, an empty stream is returned. So, limit(n) and skip(n) are complementary!
        For example, the following code skips the first two dishes that have more than 300 calories and returns the rest.
         */
        List<Dish> dishes2 = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        Utils.printList(dishes2);
    }
}
