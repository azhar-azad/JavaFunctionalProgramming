package ModernJavaInAction.c3LambdaExpressions;

import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/***
 * The java.util.function.Function<T, R> interface defines an abstract method named apply that takes an object of
 * generic type T and returns an object of generic type R.
 * Use this interface when you need to define a lambda that maps information from an input object to an output. For
 * example, extracting the weight of an apple or mapping a string to its length. In the following example, we show
 * how you can use it to create a method map to transform a list of Strings into a list of Integers containing the
 * length of each String.
 */
public class FunctionDemo {

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t: list) {
            result.add(f.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {

        List<Integer> intList = map(
                Arrays.asList("Lambdas", "in", "Action"),
                (String s) -> s.length()
        );
        Utils.printListSingleLine(intList);
    }
}
