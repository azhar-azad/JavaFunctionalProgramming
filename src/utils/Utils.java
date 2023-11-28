package utils;

import java.util.List;

public class Utils {

    /**
     * Prints a list of type T in one line separated by comma
     * @param list A list of type T
     * @param <T> The reference type of the list.
     */
    public static <T> void printListSingleLine(List<T> list) {
        System.out.println("Printing list ...");
        StringBuilder sb = new StringBuilder();
        list.forEach(t -> sb.append(t).append(", "));
        sb.delete(sb.length()-2, sb.length()-1);
        System.out.print(sb);
        System.out.println("\nPrinting done.\n");
    }

    /**
     * Prints a list of type T (each item on a new line)
     * @param list A list of type T
     * @param <T> The reference type of the list.
     */
    public static <T> void printList(List<T> list) {
        System.out.println("Printing list ...");
        list.forEach(System.out::println);
        System.out.println("Printing done.\n");
    }
}
