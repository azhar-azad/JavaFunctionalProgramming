package ModernJavaInAction.c9RefactoringTestingDebugging;

import ModernJavaInAction.c9RefactoringTestingDebugging.chainofresponsibility.HeaderTextProcessing;
import ModernJavaInAction.c9RefactoringTestingDebugging.chainofresponsibility.ProcessingObject;
import ModernJavaInAction.c9RefactoringTestingDebugging.chainofresponsibility.SpellCheckProcessing;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/***
 * The chain of responsibility pattern is a common solution to create a chain of processing objects (such as a chain
 * of operations). One processing object may do some work and pass the result to another object, which also does some
 * work and passes it on to yet another processing object, and so on.
 */
public class P4ChainOfResponsibilityPattern {

    public static void main(String[] args) {

        /*
        Generally, this pattern is implemented by defining an abstract class representing a processing object that
        defines a field to keep track of a successor. When it finishes its work, the processing object hands over its
        work to its successor.
        See the ProcessingObject class.
         */
        /*
        You can create different kinds of processing objects by subclassing the ProcessingObject class and by providing
        an implementation for the handleWork() method. You can create two processing objects doing some text processing.
        See the HeaderTextProcessing and SpellCheckProcessing classes.
         */
        /*
        Now you can connect two processing objects to construct a chain of operations.
         */
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckProcessing();
        p1.setSuccessor(p2); // Chaining two processing objects
        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result); // From Raoul, Mario and Alan: Aren't lambdas really sexy?!!

        /*
        Using Lambda Expressions
        This pattern looks like chaining (i.e. composing) functions. You can represent the processing objects as an
        instance of Function<String, String>, or (more precisely) a UnaryOperator<String>. To chain them, compose these
        functions by using the andThen method.
         */
        // The first processing object
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        // The second processing object
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        // Compose the two functions, resulting in a chain of operations.
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        String result2 = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(result2); // From Raoul, Mario and Alan: Aren't lambdas really sexy?!!
    }
}
