package ModernJavaInAction.c8CollectionAPIEnhancements;

import java.util.Map;

/***
 * Java 8 introduced several default methods supported by the Map interface. The purpose of these new operations is to
 * help you write more concise code by using a readily available idiomatic pattern instead of implementing it yourself.
 */
public class P3WorkingWithMap {

    public static void main(String[] args) {

        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);

        /*
        forEach()
        Iterating over the keys and values of a Map has traditionally been awkward. In fact, you needed to use an
        iterator of a Map.Entry<K, V> over the entry set of a Map:
         */
        for (Map.Entry<String, Integer> entry: ageOfFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend + " is " + age + " years old");
            // Raphael is 30 years old
            // Olivia is 25 years old
            // Thibaut is 26 years old
        }
        /*
        Since Java 8, the Map interface has supported the forEach() method, which accepts a BiConsumer, taking the key
        and value as arguments. Using forEach() makes your code more concise:
         */
        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " + age + " years old"));
        // Thibaut is 26 years old
        // Raphael is 30 years old
        // Olivia is 25 years old

        /*
        Sorting
        Two utilities let you sort the entries of a map by values or keys:
            - Entry.comparingByValue
            - Entry.comparingByKey
        The following code processes the elements of the stream in alphabetic order based on the person's name.
         */
        Map<String, String> favouriteMovies = Map.ofEntries(Map.entry("Raphael", "Star Wars"),
                Map.entry("Cristina", "Matrix"), Map.entry("Olivia", "James Bond"));
        favouriteMovies.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);
        // Cristina=Matrix
        // Olivia=James Bond
        // Raphael=Star Wars

        /*
        getOrDefault()
        When the key you're looking up isn't present, you receive a null reference that you have to check against to
        prevent a NullPointerException. A common design style is to provide a default value instead. Now, you can encode
        this idea more simply by using the getOrDefault() method. This method takes the key as the first argument and a
        default value (to be used when the key is absent from the Map) as the second argument.
         */
        Map<String, String> favouriteMovies2 = Map.ofEntries(Map.entry("Raphael", "Star Wars"),
                Map.entry("Olivia", "James Bond"));
        System.out.println(favouriteMovies2.getOrDefault("Olivia", "Matrix")); // James Bond
        System.out.println(favouriteMovies2.getOrDefault("Thibaut", "Matrix")); // Matrix

        /*
        Compute patterns
        Sometimes, you want to perform an operation conditionally and store its result, depending on whether a key is
        present or absent in a Map. You may want to cache the result of an expensive operation given a key, for example.
        If the key is present, there's no need to recalculate the result. Three new operations can help:
            - computeIfAbsent(): If there's no specified value for the given key (it's absent or its value is null),
            calculate a new value by using the key and add it to the Map.
            - computeIfPresent(): If the specified key is present, calculate a new value for it and add it to the Map.
            - compute(): This operation calculates a new value for a given key and stores it in the Map. 
         */
    }
}
