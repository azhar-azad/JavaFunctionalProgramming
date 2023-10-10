package fpinjava.c4designingwithlambda;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

/***
 * Dealing with Exceptions
 */
public class P9HandleException {

    /*
    We create a lambda expression that invokes a method that potentially throws
    a checked exception. We take a list of path names and ask for their
    canonical path using the getCanonicalPath() method.
     */
    public static void main(String[] args) throws IOException {

//        Stream.of("/usr", "/tmp")
//                .map(path -> new File(path).getCanonicalPath())
//                .forEach(System.out::println);
        // Error, this code will not compile

        /*
        We are limited to two options here:
            - We could either handle the exception right there within the lambda expression, or
            - Catch it and rethrow it as an unchecked exception.
        Let's try the first option:
         */
        Stream.of("/usr", "/tmp")
                .map(path -> {
                    try {
                        return new File(path).getCanonicalPath();
                    } catch (IOException e) {
                        return e.getMessage();
                    }
                })
                .forEach(System.out::println);
    }
}
