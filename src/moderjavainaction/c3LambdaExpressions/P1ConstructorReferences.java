package moderjavainaction.c3LambdaExpressions;

import moderjavainaction.c2BehaviorParameterization.Color;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class P1ConstructorReferences {

    public static void main(String[] args) {

        /*
        You can create a reference to an existing constructor using its name and the keyword new as follows:
        Classname::new. For example, suppose there's a zero-argument constructor. This fits the signature
        () -> Apple of Supplier; you can do the following:
         */
        Supplier<Apple> c1 = Apple::new; // constructor reference to the default Apple() constructor
        Apple a1 = c1.get(); // calling Supplier's get method produces a new Apple
        /*
        which is equivalent to the following:
            Supplier<Apple> c1 = () -> new Apple();
            Apple a1 = c1.get();
         */

        /*
        If you have a constructor with signature Apple(Integer weight), it fits the signature of the Function
        interface, so you can do this
         */
        Function<Integer, Apple> c2 = Apple::new; // constructor reference to Apple(Integer weight)
        Apple a2 = c2.apply(123); // calling Function's apply method with a given weight produces an Apple
        /*
        which is equivalent to the following:
            Function<Integer, Apple> c2 = (weight) -> new Apple(weight);
            Apple a2 = c2.apply(123);
         */

        /*
        In the following code, each element of a List of Integers is passed to the constructor of Apple using a
        similar map method we defined earlier, resulting in a List of apples with various weights:
         */
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new); // passing a constructor reference to the map method

        /*
        If you have a two-argument constructor, Apple(Color color, Integer weight), it fits the signature of the
        BiFunction interface, so you can do this:
         */
        BiFunction<Color, Integer, Apple> c3 = Apple::new; // constructor reference to Apple(Color color, Integer weight)
        Apple a3 = c3.apply(Color.GREEN, 123); // BiFunction's apply method with a given color and weight produces a new Apple object.
        /*
        which is equivalent to
            BiFunction<Color, Integer, Apple> c3 = (color, weight) -> new Apple(color, weight);
            Apple a3 = c3.apply(Color.GREEN, 123);
         */

        /*
        The capability of referring to a constructor without instantiating it enables interesting applications. For
        example, you can use a Map to associate constructors with a string value. You can then create a method
        giveMeFruit() that, given a String and an Integer, can create different types of fruits with different weights,
        as follows:
            Map<String, Function<Integer, Fruit>> map = new HashMap<>();
            map.put("apple", Apple::new);
            map.put("orange", Orange::new);
            // etc ...

            public Fruit giveMeFruit(String fruitName, Integer weight) {
                return map.get(fruitName.toLowerCase())
                    .apply(weight); // Function's apply method with an Integer weight parameter creates the requested Fruit. 
            }

         */
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer i: list) {
            result.add(f.apply(i));
        }
        return result;
    }
}
