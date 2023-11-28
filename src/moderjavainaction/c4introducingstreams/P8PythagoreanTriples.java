package moderjavainaction.c4introducingstreams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/***
 * The famous Greek mathematician Pythagoras discovered that certain triples of numbers (a, b, c) satisfy the formula
 * a*a + b*b = c*c where a, b, and c are integers. For example, (3, 4, 5) is a valid Pythagorean triples.
 * We are going to write a program to generate Pythagorean triples using stream.
 */
public class P8PythagoreanTriples {

    public static void main(String[] args) {

        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100)
                    .boxed()
                    .flatMap(a ->
                            IntStream.rangeClosed(a, 100)
                                    .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                    .mapToObj(b ->
                                            new int[] {a, b, (int) Math.sqrt(a*a + b*b)})
                    );

        pythagoreanTriples.limit(5)
                .forEach(t -> System.out.printf("(%d, %d, %d)\n", t[0], t[1], t[2]));

        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                                        .filter(t -> t[2] % 1 == 0)
                        );

        pythagoreanTriples2.limit(5)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ", " + t[2] + ")"));

    }
}
