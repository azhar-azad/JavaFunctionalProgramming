package moderjavainaction.c5workingwithstreams;

import utils.Utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/***
 * Building Streams
 * We can create streams from a sequence of values, from an array, from a file, and even from a generative function
 * to create infinite streams!
 */
public class P9BuildingStreams {

    public static void main(String[] args) {

        /*
        Stream from values
        You can create a stream with explicit values by using the static method Stream.of(), which can take any number
        of parameters. Also, you can get an empty stream using the empty() method.
         */
        Stream<String> stream = Stream.of("Java", "Is", "Useful");
        stream.map(String::toUpperCase).forEach(System.out::println);
        Stream<String> emptyStream = Stream.empty();

        /*
        Stream from nullable
         */
        String homeValue = System.getProperty("home"); // will produce null
        // Before Java 9
        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        // After Java 9
        homeValueStream = Stream.ofNullable(homeValue);
        /*
        This pattern can be particularly handy in conjunction with flatMap() and a stream of values that may include
        nullable objects:
         */
        Stream<String> values = Stream.of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));

        /*
        Stream from arrays
         */
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        /*
        Stream from files
         */
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct().count();
        } catch (IOException e) {

        }

        /*
        Stream from functions: creating Infinite streams!
        The Streams API provides two static methods to generate a stream from a function:
        Stream.iterate() and Stream.generate(). These two operations let you create what we call an infinite stream,
        a stream that doesn’t have a fixed size like when you create a stream from a fixed collection. Streams produced
        by iterate and generate create values on demand given a function and can therefore calculate values forever!
        It’s generally sensible to use limit(n) on such streams to avoid printing an infinite number of values.
         */
        /*
        iterate(): The iterate() method takes an initial value, and a lambda (of type UnaryOperator<T>) to apply
        successively on each new value produced. The first element of the stream is the initial value.
         */
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        /*
        In Java 9, the iterate() method was enhanced with support for a predicate. The iterate() method takes a
        predicate as its second argument that tells you when to continue iterating up until.  For example, you can
        generate numbers starting at 0 but stop iteration once the number is greater than 100:
         */
        IntStream.iterate(0, n -> n < 100, n -> n + 4)
                .forEach(System.out::println);

        /*
        generate(): Similarly to the method iterate(), the method generate() lets you produce an infinite stream of
        values computed on demand. But generate() doesn't apply successively a function on each new produced value. It
        takes a lambda of type Supplier<T> to provide new values.
         */
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
        /*
        Here's how to generate an infinite stream of ones:
         */
        IntStream ones = IntStream.generate(() -> 1);
        /*
        The following code shows how to create an IntSupplier that will return the next Fibonacci element when it's
        called. The code creates an instance of IntSupplier. This object has a mutable state: it tracks the previous
        Fibonacci element and the current Fibonacci element in two instance variables. The getAsInt() method changes
        the state of the object when it's called so that it produces new values on each call.
        In comparison, our approach using iterate() [see Quiz54] was purely immutable; you didn't modify existing state
        but were creating new tuples at each iteration.
         */
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
