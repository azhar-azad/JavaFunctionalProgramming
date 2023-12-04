package moderjavainaction.c6CollectingDataWithStreams;

import moderjavainaction.c5WorkingWithStreams.Dish;
import moderjavainaction.c5WorkingWithStreams.Type;

import java.util.*;
import java.util.stream.Collectors;

/***
 * Reducing and summarizing
 */
public class P1ReducingAndSummarizing {

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
        Finding maximum and minimum in a stream of values.
        Suppose you want to find the highest-calorie dish in the menu. You can use two collectors, Collectors.maxBy()
        and Collectors.minBy(), to calculate the maximum and minimum value in a stream. These two collectors take a
        Comparator as argument to compare the elements in the stream. Here you create a Comparator comparing dishes
        based on their calorie content and pass it to Collectors.maxBy().
        The Optional is here because what happens if the menu is empty? Then we could get a null value.
         */
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish); // Optional[pork]

        /*
        Summarization
        The Collectors class provides a specific factory method for summing: Collectors.summingInt(). It accepts a
        function that maps an object into the int that has to be summed and returns a collector that, when passed to
        the usual collect method, performs the requested summarization. For instance, you can find the total number of
        calories in your menu list with the following.
         */
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories); // 4200
        /*
        Collectors.averagingInt() is also available to calculate the average of the same set of numeric values.
         */
        double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(avgCalories); // 466.6666666666667
        /*
        Quite often, you may want to retrieve two or more of these results, and possibly you'd like to do it in a single
        operation. In this case you can use the collector returned by the summarizingInt() factory method. For example,
        you can count the elements in the menu and obtain the sum, average, maximum, and minimum of the calories
        contained in each dish with a single summarizing operation.
         */
        IntSummaryStatistics menuStat = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStat); // IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}

        /*
        Joining Strings
        The collector returned by the joining() factory method concatenates into a single string, all strings resulting
        from invoking the toString() method on each object in the stream. This means you can concatenate the names of
        all the dishes in the menu as follows.
         */
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(shortMenu); // pork, beef, chicken, french fries, rice, seasonal fruit, pizza, prawns, salmon

        /*
        Generalized summarization with reduction
        All the collectors we've discussed so far are, in reality, only convenient specializations of a reduction
        process that can be defined using the reducing() factory method. The Collectors.reducing() factory method is a
        generalization of all of them. For instance, it's possible to calculate the total calories in your menu with a
        collector created from the reducing() method as follows.
        It takes three arguments:
            - The first argument is the starting value of the reduction operation and will also be the value returned
            in the case of a stream with no elements, so clearly 0 is the appropriate value here.
            - The second argument is the function to transform a dish into an int representing its caloric content.
            - The third argument is a BinaryOperator that aggregates two items into a single value of the same type.
         */
        int totalCalories2 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories2); // 4200
        /*
        Similarly, you could find the highest-calorie dish using the one-argument version of reducing() as follows.
         */
        Optional<Dish> mostCalorieDish2 = menu.stream().collect(
                Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(mostCalorieDish2); // Optional[pork]
    }
}
