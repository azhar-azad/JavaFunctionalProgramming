package moderjavainaction.c5workingwithstreams.quiz5_2;

import utils.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Quiz52 {

    public static void main(String[] args) {

        /*
         1. Given a list of numbers how would you return a list of the square of each number?
         For example, given [1, 2, 3, 4, 5] you should return [1, 4, 9, 16, 25].
         */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> sqrNumbers = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        Utils.printListSingleLine(sqrNumbers);

        /*
        2. Given two lists of numbers, how would you return all pairs of numbers?
        For example, given a list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4),
        (3, 3), (3, 4)]. For simplicity, you can represent a pair as an array with two elements.
         */
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        pairs.forEach(pair -> {
            for (int n: pair) {
                System.out.printf(n + " ");
            }
            System.out.println();
        });
        System.out.println();

        /*
        3. How would you extend the previous example to return only pairs whose sum is divisible by 3?
         */
        List<int[]> pairsDivisibleBy3 = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        pairsDivisibleBy3.forEach(pair -> {
            for (int n: pair) {
                System.out.printf(n + " ");
            }
            System.out.println();
        });
    }
}
