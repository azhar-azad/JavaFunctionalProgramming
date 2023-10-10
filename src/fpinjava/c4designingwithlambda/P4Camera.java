package fpinjava.c4designingwithlambda;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/***
 * Designing Filters
 * Adding filters to a camera is a good example of chaining behavior or responsibilities.
 * We may start with no filters, then add a filter, and then a few more.
 * We want our design to be flexible so that it does not care how many filters we have.
 * To achieve this flexibility, we'll use Java 8 interface's default method.
 * In addition to the apply() abstract method, the Function interface has a default
 * method, compose(), to combine or chain multiple Functions.
 * The compose() method can combine or chain two Functions together. Once we compose them,
 * a call to apply() will hop through the chained Functions.
 * For example, suppose we compose two Functions, 'target' and 'next', like this:
 *      wrapper = target.compose(next);
 * Now, let's invoke the apply() method on the resulting wrapper.
 *      wrapper.apply(input);
 * The result of that call is the same as doing this (without using compose() method):
 *      temp = target.apply(input);
 *      return next.apply(temp);
 * Without the temporary variable, it would be like this:
 *      return next.apply(target.apply(input));
 * Using the compose() method, now it looks like this:
 *      wrapper = target.compose(next);
 */
@SuppressWarnings("unchecked")
public class P4Camera {

    /*
    This camera has a field for the filter, a reference to an instance of Function.
    This filter function can receive a Color and return a processed Color.
     */
    private Function<Color, Color> filter;

    /*
    We have a constructor that simply sets the filter to the dummy filter.
    Checkout the description for setFilters() method.
     */
    public P4Camera() {
        setFilters();
    }

    /*
    Here's a setFilters() method that takes a varargs of Function;
    we can send zero or more filters to this function.
    In this method, we iterate through the filters and compose them into a chain using
    the compose() method. If no filter is given, then the reduce() method will return
    an Optional empty. In that case we provide a dummy filter as an argument to the
    orElse() method, and it simply returns the color that the filter would receive for
    processing. If we provide filters to the setFilters() method, the filter field will
    refer to the first filter - an instance of Function<Color, Color> -- that's at the
    head of a chain of filters.
     */
    public void setFilters(final Function<Color, Color>... filters) {
        filter = Stream.of(filters)
                .reduce((filter, next) -> filter.compose(next))
                .orElse(color -> color);
    }

    public Color capture(final Color inputColor) {
        final Color processedColor = filter.apply(inputColor);
        //... more processing of color...
        return processedColor;
    }

    //... other functions that use the filter ...

    public static void main(String[] args) {

        /*
        Let's try out our design of the camera with filters.
         */
        final P4Camera camera = new P4Camera();

        /*
        We chose a Consumer because printing consumes the value and does not yield any results.
        This function will invoke capture() with the color value(rgb) and print the resulting
        filtered/processed output.
         */
        final Consumer<String> printCaptured = (filterInfo) ->
                System.out.printf("with %s: %s%n", filterInfo, camera.capture(new Color(200, 100, 200)));

        /*
        Let's ask the camera to capture the given colors. Since no filters are given, the
        captured color should be the same as the input.
         */
        printCaptured.accept("no filter");

        /*
        Adding a Filter
        Adding a filter is a breeze; we simply have to pass the filter to the setFilters() method. The filter
        can be a simple lambda expression or a method reference.
        As we will be seeing the output, we cna see, RGB values are higher than the corresponding values in the input.
         */
        camera.setFilters(Color::brighter);
        printCaptured.accept("brighter filter");

        /*
        Let's change the filter to a darker shade.
        This should reduce the brightness of the input.
         */
        camera.setFilters(Color::darker);
        printCaptured.accept("darker filter");

        /*
        Let's mix two filters - a brighter one and a darker one - to see the effect of chaining.
        With this filter combination, the input color goes through a series of transformations or filtering;
        first it passes through the bright filter, which brightens the shades, then it goes through the dark
        filter, which makes the colors darker again.
         */
        camera.setFilters(Color::brighter, Color::darker);
        printCaptured.accept("brighter & darker filter");

        /*
        We designed object chaining and implemented the decorator pattern without having to create a
        hierarchy of classes using the Function interface.
         */
    }
}
