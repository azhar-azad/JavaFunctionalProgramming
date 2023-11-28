package moderjavainaction.c5workingwithstreams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/***
 * Reducing
 * The terminal operations you've seen either return a boolean (allMatch() and so on), void (forEach), or an Optional
 * object (findAny and so on). You've also been using collect() to combine all elements in a stream into a List.
 * To express more complicated queries such as "Calculate the sum of all calories in the menu," or "What is the
 * highest calorie dish in the menu?" - we need to use the reduce() operation.
 */
public class P5Reducing {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        /*
        Summing the elements
        You can sum all the elements of a stream as follows:
         */
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        /*
        reduce() takes two arguments:
            - an initial value, here 0.
            - a BinaryOperator<T> to combine two elements and produce a new value;
            here you use the lambda (a, b) -> a + b

        There's also an overloaded variant of reduce() that doesn't take an initial value, but it returns an Optional
        object:
         */
        Optional<Integer> sum2 = numbers.stream().reduce((a, b) -> a + b);

        /*
        Maximum and minimum
         */
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
    }
}
