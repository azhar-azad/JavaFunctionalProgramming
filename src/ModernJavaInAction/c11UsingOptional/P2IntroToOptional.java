package ModernJavaInAction.c11UsingOptional;

/***
 * Introducing the Optional class
 * Java 8 introduces a new class called java.util.Optional<T> that's encapsulates an optional value.
 */
public class P2IntroToOptional {

    public static void main(String[] args) {

        /*
        When a value is present, the Optional class wraps it. Conversely, the absence of a value is modeled with an
        empty optional returned by the method Optional.empty. This static factory method returns a special singleton
        instance of the Optional class. Semantically a null reference and the Optional.empty() could be seen as the
        same thing, but in practice, the difference is huge. Trying to dereference a null invariably causes a
        NullPointerException, whereas Optional.empty() is a valid, workable object of type Optional that can be invoked
        in useful ways. A practical semantic difference in using Optionals instead of nulls is that in the first case,
        declaring a variable of type Optional<Car> instead of Car clearly signals that a missing value is permitted
        there. Conversely, always using the type Car and possibly assigning a null reference to a variable of that type
        implies that you don't have any help, other than your knowledge of the business model, in understanding whether
        the null belongs to the valid domain of that given variable.
        See Person, Car and Insurance classes in the optionalModel package.
         */
    }
}
