package FunctionalProgrammingInJava.c3StringsComparatorsFilters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/***
 * Listing All Files in a Directory
 */
public class P4ListFiles {

    public static void main(String[] args) throws IOException {

        /*
        Here's the code to list the names of all files in the current directory.
        We first created a Path instance from the string using the get() method of the Paths convenience class. Then,
        using the list() method of the Files utility class we got a new CloseableStream to iterate over the files in
        the given path.
         */
        Files.list(Paths.get("."))
                .forEach(System.out::println);
        System.out.println("------------------------------");

        /*
        If we want only the subdirectories in the current directory instead of a listing of all the files, we can use
        the filter() method.
         */
        Files.list(Paths.get("."))
                .filter(Files::isDirectory)
                .forEach(System.out::println);
    }
}
