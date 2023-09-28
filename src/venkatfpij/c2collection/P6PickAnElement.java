package venkatfpij.c2collection;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class P6PickAnElement {

    /*
    Let's look at the complexity introduced by the habitual approach while picking one element
    from a collection.
     */
    public static void pickName(final List<String> names, final String startingLetter) {
        String foundName = null;
        for (String name: names) {
            if (name.startsWith(startingLetter)) {
                foundName = name;
                break;
            }
        }

        System.out.printf("A name starting with %s: ", startingLetter);

        if (foundName != null) {
            System.out.println(foundName);
        } else {
            System.out.println("No name found");
        }
    }

    /*
    Let's rethink the problem. We simply want to pick the first matching element and safely deal with the absence
    of such an element. Let's rewrite the pickName() method, this time using lambda expressions.
    filter() method to grab all the elements matching the desired pattern.
    findFirst() method of the Stream class to pick the first value from that collection, which returns an Optional.
    The Optional class is useful whenever the result may be absent. We can inquire if an object is present by using
    the isPresent() method, and we can obtain the current value by using the get() method.
    Alternatively, we could suggest a substitute value for the missing instance, using the method orElse().
     */
    public static void pickName2(final List<String> names, final String startingLetter) {

        final Optional<String> foundName = names.stream()
                .filter(name -> name.startsWith(startingLetter))
                .findFirst();

        System.out.printf("A name starting with %s: %s%n", startingLetter, foundName.orElse("No name found"));
    }

    public static void main(String[] args) {

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        pickName(friends, "N");
        pickName(friends, "Z");

        pickName2(friends, "N");
        pickName2(friends, "Z");
    }
}
