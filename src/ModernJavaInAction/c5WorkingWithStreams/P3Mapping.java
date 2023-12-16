package ModernJavaInAction.c5WorkingWithStreams;

import utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 * A common data processing idiom is to select information from certain objects. For example, in SQL you can select
 * a particular column from a table. The Streams API provides similar facilities through the map() and flatMap() methods.
 */
public class P3Mapping {

    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("seasonal fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );

        /*
        Applying a function to each element of a stream
        Streams support the map() method, which takes a function as argument. The function is applied to each element,
        mapping it into a new element.
        For example, in the following code you pass a method reference to the map() method to extract the names of the
        dishes in the stream.
         */
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        Utils.printList(dishNames);

        /*
        Given a list of words, you'd like to return a list of the number of characters for each word.
         */
        List<String> words = Arrays.asList("I", "love", "Java");
        List<Integer> wordLengths = words.stream()
                .map(String::length).collect(Collectors.toList());
        Utils.printListSingleLine(wordLengths);

        /*
        Find out the length of the name of each dish.
         */
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        Utils.printListSingleLine(dishNameLengths);

        /*
        Flattening streams
        How could you return a list of all the unique characters for a list of words? For example, given the list of
        words ["Hello", "World"] you'd like to return the list ["H," "e," "l," "o," "W," "r," "d"].
        The instinctive way could be -- you can map each word into a list of characters and then call distinct to filter
        duplicate characters. Like following:
         */
        List<String> words2 = Arrays.asList("Hello", "World");
        List<String[]> characterArr = words2.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        /*
        The problem with this approach is that the lambda passed to the map() method returns a String[] for each word.
        The stream returned by the map() method is of type Stream<String[]>. What you want is Stream<String> to
        represent a stream of characters.
        The solution to this problem is to use the method flatMap().
        Using the flatMap() method has the effect of mapping each array not with a stream but with the contents of that
        stream. All the separate streams that were generated when using map(Arrays::stream) get amalgamated - flattened
        into a single stream.
        In a nutshell, the flatMap() method lets you replace each value of a stream with another stream and then
        concatenates all the generated streams into a single stream.
         */
        List<String> uniqueCharacters = words2.stream()
                .map(word -> word.split("")) // Converts each word into an array of its individual letters
                .flatMap(Arrays::stream) // Flattens each generated stream into a single stream
                .distinct()
                .collect(Collectors.toList());
        Utils.printListSingleLine(uniqueCharacters);

    }
}
