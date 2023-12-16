package ModernJavaInAction.c6CollectingDataWithStreams;

import ModernJavaInAction.c5WorkingWithStreams.Dish;
import ModernJavaInAction.c5WorkingWithStreams.Type;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/***
 * Partitioning
 */
public class P3Partitioning {

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
        Partitioning is a special case of grouping: having a predicate called a partitioning function as a classification
        function. If you're vegetarian or have invited a vegetarian friend to have dinner with you, you may be
        interested in partitioning the menu into vegetarian and non-vegetarian dishes:
         */
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu); // {false=[pork, beef, chicken, prawns, salmon], true=[french fries, rice, seasonal fruit, pizza]}
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println(vegetarianDishes); // [french fries, rice, seasonal fruit, pizza]

        /*
        Advantages of partitioning.
        Partitioning has the advantage of keeping both lists of the stream elements, for which the application of the
        partitioning function returns true or false. The partitioningBy() factory method has an overloaded version to
        which you can pass a second collector.
         */
        Map<Boolean, Map<Type, List<Dish>>> vegetarianDishesByType = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
        System.out.println(vegetarianDishesByType);
        // {false={FISH=[prawns, salmon], MEAT=[pork, beef, chicken]},
        // true={OTHER=[french fries, rice, seasonal fruit, pizza]}}
        /*
        Here the grouping of the dishes by their types is applied individually to both of the substreams of vegetarian
        and non-vegetarian dishes resulting from the partitioning, producing a two-level Map that's similar to the one
        you obtained when you performed the two-level grouping before.
        As another example, to find the most caloric dish among both vegetarian and non-vegetarian dishes:
         */
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(mostCaloricPartitionedByVegetarian); // {false=pork, true=pizza}
        /*
        It is worth noting that the Map implementation returned by partitioningBy() is more compact and efficient as it
        only needs to contain two keys: true and false. 
         */

        /*
        Partitioning numbers into prime and non-prime
        Suppose you want to write a method accepting as argument an int n and partitioning the first n natural numbers
        into prime and non-prime. But first, it will be useful to develop a predicate that tests to see if a given
        candidate number is prime or not. See isPrime(n) method below.
        Now the biggest part of the job is done. To partition the first n numbers into prime and non-prime, it's enough
        to create a stream containing those n numbers, and reduce it with a partitioningBy() collector using as
        predicate the isPrime() method. See partitionPrimes(n) method below.
        Let see it in action.
         */
        Map<Boolean, List<Integer>> partitionedPrimes = partitionPrimes(10);
        System.out.println(partitionedPrimes); // {false=[4, 6, 8, 9, 10], true=[2, 3, 5, 7]}
    }

    /*
    - Generates a range of natural numbers starting from and including 2, up to but excluding candidate.
        - A simple optimization is to test only for factors less than or equal to the square root of the candidate.
    - Returns true if the candidate isn't divisible for any of the numbers in the stream.
     */
    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
    }
}
