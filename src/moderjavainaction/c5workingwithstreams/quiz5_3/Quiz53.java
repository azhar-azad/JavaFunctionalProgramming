package moderjavainaction.c5workingwithstreams.quiz5_3;

import moderjavainaction.c5workingwithstreams.Dish;
import moderjavainaction.c5workingwithstreams.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Quiz53 {

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
        How would you count the number of dishes in a stream using the map() and reduce() methods?
        A chain of map and reduce is commonly known as the map-reduce pattern.
         */
        int count = menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
        System.out.println(count);

        long count2 = menu.stream().count();
        System.out.println(count2);
    }
}
