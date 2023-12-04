package moderjavainaction.c6CollectingDataWithStreams;

import moderjavainaction.c5WorkingWithStreams.Dish;
import moderjavainaction.c5WorkingWithStreams.Type;

import java.util.Arrays;
import java.util.List;

/***
 * The Collector interface consists of a set of methods that provide a blueprint for how to implement specific
 * reduction operations (collectors).
 */
public class P3TheCollectorInterface {

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
        Let's start by taking a look at the definition of the Collector interface, which shows the interface signature
        together with the five methods it declares. See Collector interface.
         */

        /*
        Making sense of the methods declared by Collector interface.
        Each of the first four methods returns a function that will be invoked by the collect() method, whereas the
        fifth one, characteristics(), provides a set of characteristics that's a list of hints used by the collect()
        method itself to know which optimizations (for example, parallelization) it's allowed to employ while preforming
        the reduction operation.
        See the ToListCollector class.
         */

        /*
        Note that this implementation isn't identical to the one returned by the Collectors.toList() method, but it
        differs only in some minor optimizations. These optimizations are mostly related to the fact that the collector
        provided by the Java API uses the Collections.emptyList() singleton when it has to return an empty list. This
        means that it could be safely used in place of the original Java as an example to gather a list of all the
        Dishes of a menu stream:
         */
//        List<Dish> dishes = menu.stream()
//                .collect(new ToListCollector<Dish>());
    }
}
