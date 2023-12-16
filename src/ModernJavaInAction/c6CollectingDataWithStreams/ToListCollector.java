package ModernJavaInAction.c6CollectingDataWithStreams;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector.*;

import static java.util.stream.Collector.Characteristics.*;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /*
    Making a new result container: the supplier() method
    The supplier() method has to return a Supplier of an empty accumulator - a parameterless function that when
    invoked creates an empty accumulator used during the collection process. In our ToListCollector class the
    supplier will return an empty List.
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new; // Creates the collection operation starting point
    }

    /*
    Adding an element to a result container: the accumulator() method
    The accumulator() method returns the function that performs the reduction operation. When traversing the nth element
    in the stream, this function is applied with two arguments, the accumulator being the result of the reduction (after
    having collected the first n-1 items of the stream) and the nth element itself. For ToListCollector, this function
    merely has to add the current item to the list containing the already traversed ones.
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add; // Accumulates the traversed item, modifying the accumulator in place
    }

    /*
    Applying the final transformation to the result container: the finisher() method
    The finisher() method has to return a function that's invoked at the end of the accumulation process, after having
    completely traversed the stream, in order to transform the accumulator object into the final result of the whole
    collection operation. Often, as in the case of the ToListCollector, the accumulator object already coincides with
    the final expected result. As a consequence, there's no need to preform a transformation, so the finisher() method
    has to return the identity function.
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity(); // Identifies function
    }

    /*
    Merging two result containers: the combiner() method
    The combiner() method, defines how the accumulators resulting from the reduction of different sub-parts of the
    stream are combined when the sub-parts are processed in parallel. In the toList case, the implementation of this
    method is simple; add the list containing the items gathered from the second sub-part of the stream to the end of
    the list obtained when traversing the first sub-part.
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2); // Modifies the first accumulator, combining it with the content of the second one
            return list1; // Returns the modified first accumulator
        };
    }

    /*
    The characteristics() method
    The last method, characteristics(), returns an immutable set of Characteristics, defining the behavior of the
    collector - in particular providing hints about whether the stream can be reduced in parallel and which
    optimizations are valid when doing so. Characteristics is an enumeration containing three items:
        - UNORDERED: The result of the reduction isn't affected by the order in which the items in the streams are
        traversed and accumulated.
        - CONCURRENT: The accumulator() function can be called concurrently from multiple threads, and then this
        collector can perform a parallel reduction of the stream. If the collector isn't also flagged as UNORDERED, it
        can perform a parallel reduction only when it's applied to an unordered data source.
        - IDENTITY_FINISH: This indicates the function returned by the finisher method is the identity one, and its
        application can be omitted. In this case, the accumulator object is directly used as the final result of the
        reduction precess. This also implies that it's safe to do an unchecked cast from the accumulator A to the
        result R.
    The ToListCollector developed so far is IDENTITY_FINISH, because the List used to accumulate the elements in the
    stream is already the expected final result and doesn't need any further transformation, but it isn't UNORDERED
    because if you apply it to an ordered stream you want this ordering to be preserved in the resulting List. Finally,
    it's CONCURRENT.
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                IDENTITY_FINISH, CONCURRENT)); // Flags the collector as IDENTITY_FINISH and CONCURRENT
    }
}
