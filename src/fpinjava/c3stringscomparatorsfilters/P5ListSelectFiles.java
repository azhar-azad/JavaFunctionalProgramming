package fpinjava.c3stringscomparatorsfilters;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/***
 * Listing Select Files in a Directory
 */
public class P5ListSelectFiles {

    public static void main(String[] args) throws IOException {

        String testPath = "/home/azad/Workspaces/java/FPLearning/src/venkatfpij/c2collection";

        /*
        It's a habitual practice in Java to pass the list() method an instance of an anonymous inner class that
        implements the FilenameFilter interface. Let's look at how we'd select only the java files in a
        "testPath" directory using the approach.
         */
        final String[] files = new File(testPath)
                .list(new FilenameFilter() {
                    @Override
                    public boolean accept(final File dir, final String name) {
                        return name.endsWith("java");
                    }
                });
        System.out.println(Arrays.toString(files));

        /*
        Let's use the lambda expressions to get a list of all java files in the "testPath" directory.
         */
        Files.newDirectoryStream(Paths.get(testPath), path -> path.toString().endsWith("java"))
                .forEach(System.out::println);

        /*
        We can easily pick files based on file properties, such as if a file is executable, readable, or writeable.
         */
        final File[] files2 = new File(".").listFiles(File::isHidden);
        System.out.println(Arrays.toString(files2));

    }
}
