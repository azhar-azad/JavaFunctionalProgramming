package ModernJavaInAction.c2BehaviorParameterization;

import java.util.ArrayList;
import java.util.List;

/***
 * Behavior Parameterization
 * It is a software development pattern that lets you handle frequent requirement changes. In a nutshell, it means
 * taking a block of code and making it available without executing it. This block of code can be called later by
 * other parts of your programs, which means that you can defer the execution of that block of code. For instance,
 * you could pass the block of code as an argument to another method that will execute it later.
 */
public class P1BehaviorParameterization {

    /*
    Writing code that can cope with changing requirements is difficult. For example, in the context of a farm
    inventory application, you have to implement a functionality to filter green apples from a list.
     */

    /*
    First attempt: filtering green apples
    A first solution might be as follows:
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /*
    But now the farmer changes his mind and wants to also filter red apples. A naive solution would be to duplicate
    the previous method, rename it as filterRedApples, and change the if condition to match red apples. However, this
    approach doesn't cope well with changes if the farmer wants multiple colors.
     */

    /*
    Second attempt: parameterizing the color
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    /*
    Let's complicate the example a bit. The farmer comes back to you and says, "It would be really cool to differentiate
    between light apples and heavy apples. Heavy apples typically have a weight greater than 150g.".
     */
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    /*
    To achieve this solution, you have to duplicate most of the implementation for traversing the inventory and
    applying the filtering criteria on each apple. This breaks the DRY principle of software engineering.
     */

    /*
    Third attempt: filtering with every attribute you can think of
    An ugly attempt to merge all attributes might be as follows:
     */
    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if ((flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    /*
    This solution is extremely bad. First, the client code looks terrible. What do true and false mean?
    In addition, this solution doesn't cope well with changing requirements. What if the farmer asks you to filter
    with different attributes of an apple, for example, its size, its shape, its origin, and so on? Furthermore,
    what if the farmer asks you for more complicated queries that combine attributes, such as green apples that are
    also heavy? What you need is a better way to tell your filterApples() method the selection criteria for apples.
     */

    /*
    One possible solution is to model your selection criteria: you're working with apples and returning a boolean
    based on some attributes of Apple. We call that criteria a predicate (a function that returns a boolean).
    You can now declare multiple implementation of ApplePredicate to represent different selection criteria.

    See ApplePredicate interface.
     */

    /*
    Fourth attempt: filtering by abstract criteria
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (p.test(apple)) {  // Predicate p encapsulates the condition to test on an apple.
                result.add(apple);
            }
        }
        return result;
    }

    /*
    Tackling verbosity
    At this moment, when you want to pass new behaviour to your filterApples() method, you're forced to declare
    several classes that implement the ApplePredicate interface and then instantiate several ApplePredicate objects
    that you allocate only once. That's a lot of verbosity involved and it's a time-consuming process!

    Anonymous classes
    Java has mechanisms called anonymous classes, which let you declare and instantiate a class at the same time. They
    don't have a name. They allow you to declare and instantiate a class at the same time.
     */

    /*
    Fifth attempt: using an anonymous class
    The following code shows how to rewrite the filtering example by creating an object that implements ApplePredicate
    using an anonymous class. Show main() method.

    Anonymous classes are still not good enough. First, they tend to be bulky because they take a lot of space. Second,
    many programmers find them confusing to use.
    Even though anonymous classes somewhat tackle the verbosity associated with declaring multiple concrete classes
    for an interface, they're still unsatisfactory.
     */

    /*
    Sixth attempt: using a lambda expression
    The previous code in main() method can be rewritten as follow. See the main() method.
     */

    /*
    Seventh attempt: abstracting over List type
    There's one more step that you can do in your journey toward abstraction. At the moment, the filterApples() method
    works only for Apple. But you can also abstract on the List type to go beyond the problem domain you're thinking
    of, as shown below.
     */
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) { // T is a type parameter
        List<T> result = new ArrayList<>();
        for (T t: list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }


    public static void main(String[] args) {

        List<Apple> inventory = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();

        /*
        After second attempt
         */
        List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
        List<Apple> redApples = filterApplesByColor(inventory, Color.RED);

        /*
        After third attempt
         */
        List<Apple> greenApples2 = filterApples(inventory, Color.GREEN, 0, true);
        List<Apple> heavyApples = filterApples(inventory, null, 150, false);

        /*
        After fourth attempt
         */
        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());

        /*
        After fifth attempt
         */
        List<Apple> redApples2 = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            }
        });

        /*
        After sixth attempt
         */
        List<Apple> result = filterApples(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));

        /*
        After seventh attempt
        You can use the filter() method with a List of bananas, oranges, Integers, or Strings!
         */
        List<Apple> redApples3 = filter(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);

    }
}
