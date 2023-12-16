package ModernJavaInAction.c5WorkingWithStreams;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/***
 * Streams API supplies primitive stream specializations that support specialized methods to work with
 * streams of numbers.
 * The problem with map-reduce patterns is that there's an insidious boxing cost.
 */
public class P7NumericStream {

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
        Primitive stream specialization
        Java 8 introduces three primitive specialized stream interfaces, IntStream, DoubleStream, and LongStream,
        which respectively specialize the elements of a stream to int, double, and long - and they avoid hidden
        boxing costs. Each of these interfaces brings new methods to perform common numeric reductions, such as
            - sum(): to calculate the sum of a numeric stream,
            - max(): to find the maximum element,
            - min(): to find the minimum element,
            - avg(): to find the average of a numeric stream, etc.
         */

        /*
        Mapping to a numeric stream
        The most common methods you'll use to convert a stream to a specialized version are mapToInt(), mapToDouble(),
        and mapToLong(). These methods work exactly like the method map() but return a specialized stream instead of
        Stream<T>. For example, you can use mapToInt() as follows to calculate the sum of calories in the menu:
         */
        int calories = menu.stream() // Returns a Stream<Dish>
                .mapToInt(Dish::getCalories) // Returns an IntStream, rather than a Stream<Integer>
                .sum();
        System.out.println(calories); // 4200

        /*
        Converting back to a stream of objects
        The operations of an IntStream are restricted to produce primitive integers: the map() operation of an IntStream
        takes a lambda that takes an int and produces an int. But you may want to produce a different value such as Dish.
        To convert from a primitive stream to a general stream (each int will be boxed to an Integer) you can use the
        method boxed(), as follows:
         */
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories); // Converts a Stream to a numeric stream
        Stream<Integer> stream = intStream.boxed(); // Converts the numeric stream to a Stream

        /*
        Default values: OptionalInt, OptionalDouble, OptionalLong
        There's a primitive specialized version of Optional as well for the three primitive stream specializations:
        OptionalInt, OptionalDouble, and OptionalLong. For example, you can find the maximal element of an IntStream
        by calling the max() method, which returns an OptionalInt. You can process the OptionalInt explicitly to
        define a default value if there's no maximum.
         */
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1);
        System.out.println(max); // 800

        /*
        Numeric ranges
        Suppose you'd like to generate all numbers between 1 and 100. Java 8 introduces two static methods available
        on IntStream and LongStream to help generate such ranges: range() and rangeClosed(). range() is exclusive, and
        rangeClosed() is inclusive.
         */
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count()); // 50
        evenNumbers = IntStream.range(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count()); // 49
    }
}
