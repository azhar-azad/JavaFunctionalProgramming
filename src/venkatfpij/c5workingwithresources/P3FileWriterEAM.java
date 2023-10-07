package venkatfpij.c5workingwithresources;

import java.io.FileWriter;
import java.io.IOException;

/***
 * Using Lambda Expressions to Clean Up Resources
 * FileWriterEAM encapsulates heavy resources that need timely cleanup.
 * Let's make both the constructor and the close() method private -
 * that'll grab the attention of programmers trying to use the class.
 */
public class P3FileWriterEAM {

    private final FileWriter writer;

    private P3FileWriterEAM(final String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }

    private void close() throws IOException {
        System.out.println("close called automatically...");
        writer.close();
    }

    public void writeStuff(final String message) throws IOException {
        writer.write(message);
    }

    //...

    /*
    Since the programmers can't directly create an instance of FileWriterEAM, we need
    a factory method for them to use. Unlike the regular factory methods, this method
    will yield it to users and wait for them to finish their work with it.
    In the use() method, we receive two parameters, fileName and a reference to an
    interface UseInstance. Within the method we instantiate FileWriterEAM, and within
    the safe heaven of the try and finally block we pass the instance to an accept()
    method of UseInstance interface.
    The use() method represents the structure of the 'execute around method' pattern.
    The main action here is the use of the instance within accept() method, but the
    creation and cleanup operations nicely surround this call.
     */
    public static void use(final String fileName,
            final UseInstance<P3FileWriterEAM, IOException> block) throws IOException {

        final P3FileWriterEAM writerEAM = new P3FileWriterEAM(fileName);
        try {
            block.accept(writerEAM);
        } finally {
            writerEAM.close();
        }
    }

    public static void main(String[] args) throws IOException {

        P3FileWriterEAM.use("./src/venkatfpij/c5workingwithresources/peekaboo_eam.txt",
                writeEAM -> writeEAM.writeStuff("sweet"));

        /*
        First, our class's users can't create an instance directly. This prevents them from creating
        code that would postpone the resource cleanup beyond its expiration point.
        Since the compiler will prevent calls to the constructor or the close() method, the
        programmers will quickly figure out the benefits of the use() method, which yields an
        instance for their use. To invoke use, the can use the lambda expressions as we saw on the
        code example.
         */

        /*
        We can perform multiple operations by using multiline syntax.
        To bear in mind, we would lose the benefit of code that's concise, easy to understand, and
        simple to maintain, if the lambda expression is long. Instead of writing long lambda
        expressions, we should move the code into other methods and then use method references for
        them if possible, or invoke them from within a lambda expression.
         */
        P3FileWriterEAM.use("./src/venkatfpij/c5workingwithresources/peekaboo_eam.txt",
                writeEAM -> {
                    writeEAM.writeStuff("how");
                    writeEAM.writeStuff(" sweet");
                });

        /*
        In our example, the UseInstance's accept() is a void method. If we were interested in returning
        some results to the caller of the use() method, we'd have to modify this method's signature to
        place an appropriate return type, such as a generic parameter R. If we were to make this change,
        then the UseInstance would be more like Function<U, R> interface than like the Consumer<T> interface.
        We'd also have to change the use() method to propagate the return results from the modified apply() method.
         */
    }
}
