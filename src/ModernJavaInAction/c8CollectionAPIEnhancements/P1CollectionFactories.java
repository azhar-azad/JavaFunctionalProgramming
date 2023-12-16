package ModernJavaInAction.c8CollectionAPIEnhancements;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class P1CollectionFactories {

    public static void main(String[] args) {

        /*
        List factory
        You can create a list simply by calling the factory method List.of()
         */
        List<String> friendList = List.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friendList); // [Raphael, Olivia, Thibaut]
        /*
        Try to add an element to your list will result a java.lang.UnsupportedOperationException. In fact, the list
        that's produced is immutable. Replacing an item with the set() method throws a similar exception. This
        restriction is to protect you from unwanted mutations of the collections. Finally, note that to prevent
        unexpected bugs and enable a more-compact internal representation, null elements are disallowed.
         */
//        friendList.add("Chih-Chun");
        // Exception in thread "main" java.lang.UnsupportedOperationException
        //	at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)
        //	at java.base/java.util.ImmutableCollections$AbstractImmutableCollection.add(ImmutableCollections.java:147)
        //	at moderjavainaction.c8CollectionAPIEnhancements.P1CollectionFactories.main(P1CollectionFactories.java:15)

        /*
        Set factory
        As with List.of(), you can create an immutable Set out of a list elements.
         */
        Set<String> friendSet = Set.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friendSet); // [Raphael, Thibaut, Olivia]

        /*
        Map factories
        You have two ways to initialize an immutable map in Java 9. You can use the factory method Map.of(), which
        alternates between keys and values.
         */
        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
        System.out.println(ageOfFriends); // {Olivia=25, Thibaut=26, Raphael=30}
        /*
        This method is convenient if you want to create a small map of up to ten keys and values. To go beyond this, use
        the alternative factory method called Map.ofEntries(), which takes Map.Entry<K, V> objects but is implemented
        with varargs. This method requires additional object allocations to wrap up a key and a value.
         */
        Map<String, Integer> ageOfFriends2 = Map.ofEntries(Map.entry("Raphael", 30),
                Map.entry("Olivia", 25), Map.entry("Thibaut", 26));
        System.out.println(ageOfFriends2); // {Olivia=25, Thibaut=26, Raphael=30}
    }
}
