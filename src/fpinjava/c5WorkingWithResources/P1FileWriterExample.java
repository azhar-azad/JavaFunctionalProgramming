package fpinjava.c5WorkingWithResources;

import java.io.FileWriter;
import java.io.IOException;

/***
 * Cleaning Up Resources
 * Here's an example problem that involves Garbage Collection.
 */
public class P1FileWriterExample {

    private final FileWriter writer;

    public P1FileWriterExample(final String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }

    public void writeStuff(final String message) throws IOException {
        writer.write(message);
    }

    /*
    We clean up the resource, calling close() on it with the hope that
    it will flush the content to the file and close it.
     */
    protected void finalize() throws IOException {
        writer.close();
    }

    //...

    /*
    Let's fix the problem by adding an explicit call to close(), and let's get rid of the finalize() method. 
     */
    public void close() throws IOException {
        writer.close();
    }

    public static void main(String[] args) throws IOException {

        final P1FileWriterExample writerExample = new P1FileWriterExample("./src/venkatfpij/c5workingwithresources/peekaboo.txt");
        writerExample.writeStuff("peek-a-boo");

        /*
        We created an instance of the FileWriterExample class and invoked the writeStuff() method on it,
        but if we ran this code, we'd see that the peekaboo.txt file was created but it's empty. The
        finalizer never ran; the JVM decides it wasn't necessary as there was enough memory. As a result,
        the file never closed, and the content we wrote was not flushed from memory.
         */
        writerExample.close();

        /*
        Ensuring Cleanup
        The explicit call to close() cleans up any external resources the instance uses, as soon as we
        indicate the instance is no longer needed. However, we may not reach the call to the close()
        method if there was an exception in the code leading up to it. We'll have to do a bit more work
        to ensure the call to close() happens. Full re-factorization of the above code is like this:
         */

        final P1FileWriterExample writerExample2 = new P1FileWriterExample("./src/venkatfpij/c5workingwithresources/peekaboo2.txt");
        try {
            writerExample2.writeStuff("peek-a-boo2");
        } finally {
            writerExample2.close();
        }

    }
}
