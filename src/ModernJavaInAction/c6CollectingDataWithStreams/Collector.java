package ModernJavaInAction.c6CollectingDataWithStreams;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector.*;

/***
 * In the interface, the following definitions apply:
 * @param <T> is the generic type of the items in the stream to be collected.
 * @param <A> is the type of the accumulator, the object on which the partial result will be accumulated during the
 *           collection process.
 * @param <R> is the type of the object (typically, but not always, the collection) resulting from the collect
 *           operation.
 *
 * For instance, you could implement a ToListCollector<T> class that gathers all the elements of a Stream<T> into a
 * List<T> having the following signature:
 *           public class ToListCollector<T> implements Collector<T, List<T>, List<T>>
 * where, the object used for the accumulation process will also be the final result of the collection process.
 */
public interface Collector<T, A, R> {
    Supplier<A> supplier();
    BiConsumer<A, T> accumulator();
    Function<A, R> finisher();
    BinaryOperator<A> combiner();
    Set<Characteristics> characteristics();
}
