package fpinjava.c5WorkingWithResources;

import java.io.FileWriter;
import java.io.IOException;

/***
 * Using ARM
 * ARM can reduce the verbosity in the previous example. Rather than using both the
 * try and finally blocks, we can use a special form of the try block with a
 * resource attached to it. Then the Java compiler takes care of automatically
 * inserting the finally block and the call to the close() method.
 */
public class P2FileWriterARM implements AutoCloseable {

    private final FileWriter writer;

    public P2FileWriterARM(final String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }

    public void writeStuff(final String message) throws IOException {
        writer.write(message);
    }

    @Override
    public void close() throws Exception {
        System.out.println("close called automatically...");
        writer.close();
    }

    //...

    public static void main(String[] args) throws Exception {

        try (final P2FileWriterARM writerARM = new P2FileWriterARM("./src/venkatfpij/c5workingwithresources/peekaboo_arm.txt")) {
            writerARM.writeStuff("peek-a-boo-arm");

            System.out.println("done with the resource...");
        }

        /*
        The previous code using ARM is quite concise and charming, but the programmers have
        to remember to use it. If we're looking for a way to really ensure timely cleanup
        and avoid programmer errors, we have to look beyond ARM, as we'll do next.
         */
    }
}
