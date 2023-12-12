package moderjavainaction.c11UsingOptional;

import java.util.Optional;
import java.util.Properties;

/***
 * Practical examples of using Optional
 */
public class P4PracticalExamples {

    public static void main(String[] args) {

        /*
        Exceptions vs Optional
        Throwing an exception is another common alternative in the Java API to returning a null when a value can't be
        provided. A typical example is the conversion of String into an int provided by the Integer.parseInt(String)
        static method. You can implement a tiny utility method, wrapping it, and returning an optional as desired.
        See the OptionalUtility.stringToInt() method.
         */

        /*
        Suppose that you have some Properties that are passed as configuration arguments to your program.
        For this purpose of this example and to test the code you'll develop, create some sample Properties as follows:
         */
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");
        /*
        Also suppose that your program needs to read a value from these Properties and interpret it as a duration in
        seconds. Because a duration has to be positive (>0) number, you'll want a method with the signature
            public int readDuration(Properties props, String name)
        so that when the value of a given property is a String representing a positive integer, the method returns that
        integer, but it returns zero in all other cases.
        Let's try to implement the method that satisfy this requirement in imperative style.
        See readDurationImperative() method.
         */
        /*
        Because the value returned by the Properties.getProperty(String) method is a null when the required property
        doesn't exist, it's convention to turn this value into an optional with the ofNullable factory method. Then you
        can convert the Optional<String> to an Optional<Integer>, passing to its flatMap() method a reference to the
        OptionalUtility.stringToInt() method. Finally, you can easily filter away the negative number. In this way, if
        any of these operations returns an empty optional, the method returns the 0 that's passed as the default value
        to the orElse() method; otherwise, it returns the positive integer contained in the optional.
        See the readDuration() method.
         */
    }

    public static int readDurationImperative(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) { // Make sure that a property exists with the required name.
            try {
                int i = Integer.parseInt(value); // Try to convert the String property to a number.
                if (i > 0) { // Check whether the resulting number is positive.
                    return i;
                }
            } catch (NumberFormatException nfe) {

            }
        }
        return 0; // Return 0 if any of the conditions fails.
    }

    public static int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalUtility::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }
}
