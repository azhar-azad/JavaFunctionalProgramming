package fpinjava.c3stringscomparatorsfilters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/***
 * Listing Immediate Subdirectories Using flatMap
 */
public class P6ListSubDirs {

    /**
     * Let's look at the effort to explore the immediate (one level deep) subdirectories in a given directory,
     * first using a rudimentary operation and then, more conveniently, using the flatMap() method.
     */
    public static void main(String[] args) {

        listTheHardWay();
        listBetterWay();

    }

    /**
     * Let's use the traditional for loop first to iterate over the files in a given directory. If a subdirectory
     * contains any files, we'll add them to our list; otherwise, we'll add the subdirectory itself to the list.
     * Finally, we'll print the total number of files found.
     */
    public static void listTheHardWay() {
        List<File> files = new ArrayList<>();

        File[] filesInCurrentDir = new File(".").listFiles();
        assert filesInCurrentDir != null;
        for (File file: filesInCurrentDir) {
            File[] filesInSubDir = file.listFiles();
            if (filesInSubDir != null) {
                files.addAll(Arrays.asList(filesInSubDir));
            } else {
                files.add(file);
            }
        }

        System.out.println("Count: " + files.size());
    }

    /**
     * flatMap() maps the elements in a collection, much like the map() method does. But unlike the map() method,
     * where we generally return an element from the lambda expression, we return Stream instead. The method then
     * flattens the multiple streams, obtained by mapping each element, into one flat stream.
     */
    public static void listBetterWay() {
        List<File> files = Stream.of(Objects.requireNonNull(new File(".").listFiles()))
                .flatMap(file -> file.listFiles() == null ?
                        Stream.of(file) : Stream.of(Objects.requireNonNull(file.listFiles())))
                .toList();
        System.out.println("Count: " + files.size());
    }
}
