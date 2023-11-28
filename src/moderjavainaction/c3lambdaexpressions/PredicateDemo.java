package moderjavainaction.c3lambdaexpressions;

import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/***
 * The java.util.function.Predicate<T> interface defines an abstract method named test that accepts an object of
 * generic type T and returns a boolean.
 * Use this interface when you need to represent a boolean expression that uses an object of type T. For example,
 * you can define a lambda that accepts String objects, as shown in this demo.
 */
public class PredicateDemo {

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t: list) {
            if (p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    public static void main(String[] args) {

        List<String> listOfStrings = Arrays.asList("Azad", "N", "", "100", "", "Not empty");

        Predicate<String> nonEmptyStrings = (String s) -> !s.isEmpty();
        Predicate<String> emptyStrings = (String s) -> s.isEmpty();

        List<String> nonEmpty = filter(listOfStrings, nonEmptyStrings);
        List<String> empty = filter(listOfStrings, emptyStrings);

        Utils.printListSingleLine(nonEmpty);
        Utils.printListSingleLine(empty);
    }
}
