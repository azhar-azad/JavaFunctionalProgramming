package chainingAndCompose;

import java.util.function.Function;
import java.util.logging.Logger;

public class FunctionChainingAndComposeDemo {

    static Logger logger = Logger.getLogger(FunctionChainingAndComposeDemo.class.getName());

    private static Function<Integer, Integer> multiply = x -> x * 2;

    private static Function<Integer, Integer> add = x -> x + 2;

    private static Function<Integer, Void> logOutput = x -> {
        logger.info("Data: " + x);
        return null;
    };

    public static Void execute(Integer input) {
        Function<Integer, Void> pipeline = multiply.andThen(add).andThen(logOutput);
        return pipeline.apply(input);
    }

    public static void main(String[] args) {
        System.out.println("add.apply = " + add.apply(10));
        System.out.println("multiply.apply = " + multiply.apply(10));
        System.out.println("logOutput = ");
        logOutput.apply(10);
        System.out.println("execute = ");
        execute(10);
        System.out.println("compose = ");
        compose(10);
    }

    public static Void compose(Integer input) {
        Function<Integer, Void> pipeline = logOutput.compose(add).compose(multiply);
        return pipeline.apply(input);
    }
}
