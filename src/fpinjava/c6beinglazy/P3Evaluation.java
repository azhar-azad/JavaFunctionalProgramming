package fpinjava.c6beinglazy;

import java.util.function.Supplier;

/***
 * We will explore delaying running methods, and use that approach
 * to improve the design. Let's start Eager Evaluation (regular approach).
 */
public class P3Evaluation {

    /*
    A call to evaluate would take a couple of seconds to run, so we definitely
    want to postpone any unnecessary calls.
     */
    public static boolean evaluate(final int value) {
        System.out.println("evaluating ..." + value);
//        simulateTimeConsumingOp(2000);
        return value > 100;
    }

    /*
    eagerEvaluator() is like almost any method we write in Java: all of its
    arguments will be evaluated before its call.
    This method takes two boolean parameters. Within the method we perform a
    logical and operation on the parameters. Sadly, it's too late to benefit
    from the lazy evaluation this operation automatically provides since the
    arguments are evaluated well before we enter this method.
     */
    public static void eagerEvaluator(final boolean input1, final boolean input2) {
        System.out.println("eagerEvaluator called...");
        System.out.println("accept?: " + (input1 && input2));
    }

    /*
    If we know that some arguments may not be used during the execution of a method,
    we can design the method's interface to facilitate the delayed execution of some
    or all arguments. The arguments can be evaluated on demand, like the following
    method.
    Rather than taking two boolean parameters, the method receives references to
    the Supplier instances. This JDK functional interface will return an instance,
    Boolean in this case, in response to a call to its get() method. The logical
    and operation we use within the lazyEvaluator() method will invoke the get()
    methods on demand.
     */
    public static void lazyEvaluator(
            final Supplier<Boolean> input1, final Supplier<Boolean> input2) {
        System.out.println("lazyEvaluator called...");
        System.out.println("accept?: " + (input1.get() && input2.get()));
    }

    public static void main(String[] args) {

        /*
        Here we can see both the calls to evaluate() execute way before we enter
        the eagerEvaluator() method.
         */
        eagerEvaluator(evaluate(1), evaluate(2));

        /*
        If we pass two calls to evaluate() as arguments to the lazyEvaluator()
        method, the second will be evaluated only if the first call returned a
        boolean true.
         */
        lazyEvaluator(() -> evaluate(1), () -> evaluate(2));

        /*
        This technique can significantly boost performance, but its disadvantage
        is that it burdens the caller with packaging the calls in the lambda
        expression. In some contexts we may be able to use method references
        instead of lambda expressions, and this can make the code a bit more
        concise and ease the burden a little.
        Let's use it purely for convenience next.
         */
    }
}
