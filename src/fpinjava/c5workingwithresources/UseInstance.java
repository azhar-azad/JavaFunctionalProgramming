package fpinjava.c5workingwithresources;

/***
 * UseInstance is a functional interface, an ideal candidate for the Java
 * compiler to automatically synthesize from lambda expressions or method
 * references. Whether we use this interface or not, the compiler will
 * automatically recognize functional interfaces structurally. 
 * @param <T> A generic type so that the accept() method can accept an instance
 *           of it. In this example we tied it down to an instance of a concrete
 *           FileWriterEAM.
 * @param <X> The accept() method implementation could potentially throw a generic
 *           exception X - again, in this example, it is tied to the concrete
 *           class IOException.
 */
@FunctionalInterface
public interface UseInstance<T, X extends Throwable> {
    void accept(T instance) throws X;
}
