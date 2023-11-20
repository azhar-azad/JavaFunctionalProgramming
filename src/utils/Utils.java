package utils;

import java.util.List;

public class Utils {

    /**
     * Prints a list of type T
     * @param list A list of type T
     * @param inOneLine A boolean to indicate printing of each item on new line or every item on one line separated by space.
     * @param <T> The reference type of the list.
     */
    public static <T> void printList(List<T> list, boolean inOneLine) {
        System.out.println("Printing list ...");
        if (inOneLine) {
            list.forEach(t -> System.out.print(t + " "));
            System.out.println();
        } else {
            list.forEach(System.out::println);
        }
        System.out.println("Printing done.");
    }
}
