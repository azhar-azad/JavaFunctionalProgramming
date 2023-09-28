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

         */
    }

    public static void printChar(int aChar) {
        System.out.println((char) aChar);
    }
}
