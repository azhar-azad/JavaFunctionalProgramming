package moderjavainaction.c11UsingOptional;

import java.util.Optional;

public class OptionalUtility {

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s)); // If the String can be converted to an Integer, return an optional containing it.
        } catch (NumberFormatException nfe) {
            return Optional.empty(); // Otherwise, return an empty optional.
        }
    }
}
