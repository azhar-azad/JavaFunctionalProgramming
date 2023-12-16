package ModernJavaInAction.c9RefactoringTestingDebugging;

import ModernJavaInAction.c9RefactoringTestingDebugging.strategy.IsAllLowerCase;
import ModernJavaInAction.c9RefactoringTestingDebugging.strategy.IsNumeric;
import ModernJavaInAction.c9RefactoringTestingDebugging.strategy.Validator;

/***
 * Strategy
 * The strategy pattern is a common solution for representing a family of algorithms and letting you choose among them
 * at runtime. The strategy pattern consists of three parts:
 *      - An interface to represent some algorithm.
 *      - One or more concrete implementations of that interface to represent multiple algorithms.
 *      - One or more clients that use the strategy objects.
 */
public class P1StrategyPattern {

    public static void main(String[] args) {

        /*
        Suppose that you'd like to validate whether a text input is properly formatted for different criteria. You start
        by defining an interface to validate the text.
        See ValidationStrategy interface.
         */
        /*
        Second you define one or more implementation(s) of that interface.
        See IsAllLowerCase and IsNumeric classes.
         */
        /*
        Then you can use these different validation strategies in your program.
        See Validator class.
         */
        Validator numericValidator = new Validator(new IsNumeric());
        System.out.println(numericValidator.validate("aaaa")); // false
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        System.out.println(lowerCaseValidator.validate("bbbb")); // true

        /*
        Using Lambda Expression
        You should recognize that ValidationStrategy is a function interface. In addition, it has the same function
        descriptor as Predicate<String>. As a result, instead of declaring new classes to implement different
        strategies, you can pass more concise lambda expressions directly.
         */
        Validator numericValidator2  = new Validator((String s) -> s.matches("//d+"));
        System.out.println(numericValidator2.validate("aaaa")); // false
        Validator lowerCaseValidator2 = new Validator((String s) -> s.matches("[a-z]+"));
        System.out.println(lowerCaseValidator2.validate("bbbb")); // true

        /*
        As you can see, lambda expressions remove the boilerplate code that's inherent to the strategy design pattern.
        If you think about it, lambda expressions encapsulate a piece of code (or strategy), which is what the strategy
        design pattern was created for, so we recommend that you use lambda expressions instead for similar problems.
         */
    }
}
