package venkatfpij.c3stringscomparatorsfilters;

/***
 * Iterating a String and some Method References
 */
public class P1IterateString {

    public static void main(String[] args) {

        final String str = "w00t";

        /*
        The chars() method is a new method in the String class from the CharSequence interface.
        It returns a stream of Integers representing the letters instead of a stream of Characters.
         */
        str.chars().forEach(System.out::println);

        /*
        Although the code is concise, the output is not satisfactory. We want to see letters and not
        numbers in their place. To fix that, let's write a convenience method printChar(int).
         */
        str.chars().forEach(P1IterateString::printChar);

        /*
        If we want to process characters and not int from the start, we can convert this ints to
        characters right after the call to the chars() method.
         */
        str.chars().mapToObj(ch -> Character.valueOf((char) ch))
                .forEach(System.out::println);

        /*
        About Method Reference
        Method References can be applicable to both instance methods and static methods. They look
        the same structurally.
        If it's an instance method, then the synthesized method's parameter becomes the call's target,
        like in parameter.toUpperCase(); [An exception is System.out::println()].
        If it's an static method, then the parameter to the synthesized method is routed as an
        argument to this method, like in Character.isDigit(parameter) or P1IterateString.printChar(parameter).
        There is an caveat - method collisions and the resulting ambiguity. If there's both a matching
        instance method and a static method, we'll get a compilation error due to the reference's ambiguity.
        For example, if we write Double::toString to convert an instance of Double to String, the compiler
        would get confused whether to use the 'public String toString()' instance method or the static
        method 'public static String toString(double value)', both from the Double class.
        If we run into this, we simply switch back to using the appropriate lambda-expression version to move on.
         */
    }

    public static void printChar(int aChar) {
        System.out.println((char) aChar);
    }
}
