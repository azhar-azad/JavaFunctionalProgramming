package ModernJavaInAction.c6CollectingDataWithStreams;

import ModernJavaInAction.c5WorkingWithStreams.Dish;
import ModernJavaInAction.c5WorkingWithStreams.Type;

import java.util.*;
import java.util.stream.Collectors;

/***
 * Grouping
 */
public class P2Grouping {

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
        Suppose you want to classify the dishes in the menu according to their type, putting the ones containing meat
        in a group, the ones with fish in another group, and all others in a third group. You can easily perform this
        task using a collector returned by the Collectors.groupingBy() factory method.
         */
        Map<Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);
        // {FISH=[prawns, salmon], MEAT=[pork, beef, chicken], OTHER=[french fries, rice, seasonal fruit, pizza]}
        /*
        Here, you pass to the groupingBy() method a Function extracting the corresponding Type for each Dish in the
        stream. We call this Function a classification function specifically because it's used to classify the elements
        of the stream into different groups. But it isn't always possible to use a method reference as a classification
        function, because you may wish to classify using something more complex than a simple property accessor. For
        instance, you could decide to classify as "diet" all dishes with 400 calories or fewer, set to "normal" the
        dishes having between 400 and 700 calories, and set to "fat" the ones with more than 700 calories. Because the
        author of the Dish class unhelpfully didn't provide such an operation as a method, you can't use a method
        reference in this case, but you can express this login in a lambda expression.
         */
        enum CaloricLevel { DIET, NORMAL, FAT }
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })
        );
        System.out.println(dishesByCaloricLevel);
        // {DIET=[chicken, rice, seasonal fruit, prawns], FAT=[pork], NORMAL=[beef, french fries, pizza, salmon]}

        /*
        Manipulating grouped elements
        Frequently after performing a grouping operation you may need to manipulate the elements in each resulting
        group. Suppose, that you want to filter only the caloric dishes, let's say the ones with more than 500 calories.
        In this case you could apply this filtering predicate before the grouping like the following:
         */
        Map<Type, List<Dish>> caloricDishesByType = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println(caloricDishesByType); // {MEAT=[pork, beef], OTHER=[french fries, pizza]}
        /*
        This solution works but has a possibly relevant drawback. If you try to use it on the dishes in our menu, you
        will obtain a Map like above.
        The problem here is that, there is no dish of type FISH satisfying our filtering predicate, that key totally
        disappeared from the resulting map.
        To work around this problem the Collectors class overloads the groupingBy() factory method, with one variant
        also taking a second argument of type Collector along with the usual classification function. In this way, it's
        possible to move the filtering predicate inside this second Collector, as follows.
         */
        Map<Type, List<Dish>> caloricDishesByType2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())));
        System.out.println(caloricDishesByType2); // {FISH=[], MEAT=[pork, beef], OTHER=[french fries, pizza]}
        /*
        The filtering() method is another static factory method of the Collectors class accepting a Predicate to filter
        the elements in each group and a further Collector that is used to regroup the filtered elements.
         */
        /*
        Another even more common way in which it could be useful to manipulate the grouped elements is transforming
        them through a mapping function. By using it you can, for instance, convert each Dish in the groups into their
        respective names in this way.
         */
        Map<Type, List<String>> dishNamesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(Dish::getName, Collectors.toList())));
        System.out.println(dishNamesByType);
        // {FISH=[prawns, salmon], MEAT=[pork, beef, chicken], OTHER=[french fries, rice, seasonal fruit, pizza]}

        /*
        Multilevel grouping
        The two arguments Collectors.groupingBy() factory method that we used in a former section to manipulate the
        elements in the groups resulting from the grouping operation can be used also to perform a two-level grouping.
        To achieve this you can pass to it a second inner groupingBy() to the outer groupingBy(), defining a
        second-level criterion to classify the stream's items.
         */
        Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })));
        System.out.println(dishesByTypeCaloricLevel);
        // {
        //  FISH={DIET=[prawns], NORMAL=[salmon]},
        //  MEAT={DIET=[chicken], FAT=[pork], NORMAL=[beef]},
        //  OTHER={DIET=[rice, seasonal fruit], NORMAL=[french fries, pizza]}
        // }

        /*
        Collecting data in subgroups
        Generally, the second collector passed to the first groupingBy() can be any type of Collector, not just another
        groupingBy(). For instance, it's possible to count the number of Dishes in the menu for each type, by passing
        the counting() collector as a second argument to the groupingBy() collector.
         */
        Map<Type, Long> typesCount = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(typesCount); // {FISH=2, MEAT=3, OTHER=4}
        /*
        Also note that, the regular one-argument groupingBy(f), where f is the classification function is, in reality,
        shorthand for groupingBy(f, toList()). To give another example, you could rework the collector you already used
        to find the highest-calorie dish in the menu to achieve a similar result, but now classified by the type of dish.
         */
        Map<Type, Optional<Dish>> mostCaloricByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType); // {FISH=Optional[salmon], MEAT=Optional[pork], OTHER=Optional[pizza]}
        /*
        If there's no Dish in the menu for a given type, that type won't have an Optional.empty() as value; it won't be
        present at all as a key in the Map. To get rid of this problem, or more generally, to adapt the result returned
        by a collector to a different type, you could use the collector returned by the Collectors.collectingAndThen()
        factory method.
         */
        Map<Type, Dish> mostCaloricByType2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(mostCaloricByType2); // {FISH=salmon, MEAT=pork, OTHER=pizza}
    }
}
