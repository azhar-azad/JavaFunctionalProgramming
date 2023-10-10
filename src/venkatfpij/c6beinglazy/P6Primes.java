package venkatfpij.c6beinglazy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/***
 * Creating Infinite, Lazy Collections
 */
public class P6Primes {

    /*
    A Desperate Attempt
    Let's create a helper function to determine if a number is prime.
    The rangeClosed() method is special variate of the range() method with a
    difference on including or not-including the second parameter. For example,
    rangeClosed(1, 10) will return a range of values 1, 2, ..., 10 packed into a
    Stream. In contrast, the range() method will return a range of values, up to
    (but not including) the value of the second parameter.
    We also use the noneMatch() method on the stream returned by the rangeClosed()
    method. It takes a Predicate as its parameter, and we use this to determine if
    there's a divisor for the given number. The noneMatch() method will yield a
    boolean true if the lambda expression returned false for all values in the
    range - that is, if there are no divisiors.
     */
    public static boolean isPrime(final int number) {
        return number > 1 &&
                IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(divisor -> number % divisor == 0);
    }

    /*
    The primeAfter() method returns a prime number that's after the given number.
    If the number next to the given number is prime, it is immediately returned;
    otherwise, the method recursively asks for the prime number that follows.
     */
    private static int primeAfter(final int number) {
        if (isPrime(number + 1))
            return number + 1;
        else
            return primeAfter(number + 1);
    }

    /*
    The primes() method deals with the infinite series. It will create an infinite
    series of prime numbers, starting with the first prime greater than or equal
    to the number given as parameter. In the call to the iterate() method, the
    first parameter provides the seed for the infinite series. If the given number
    is prime, it's used as the seed. Otherwise, the first prime after the number is
    used. The second parameter, a method reference, stands in for a UnaryOperator
    that takes in a parameter and returns a value. In this example, since we refer
    to the primeAfter() method, it takes in a number and returns a prime after the
    number.
     */
    public static List<Integer> primes(final int fromNumber, final int count) {
        return Stream.iterate(primeAfter(fromNumber - 1), P6Primes::primeAfter)
                .limit(count)
                .collect(Collectors.<Integer>toList());
    }

    public static void main(String[] args) {

        /*
        Ten primes starting at 1
         */
        System.out.println("10 primes from 1: " + primes(1, 10));

        /*
        Five primes starting at 100
         */
        System.out.println("5 primes from 100: " + primes(100, 5));
    }
}
