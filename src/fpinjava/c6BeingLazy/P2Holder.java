package fpinjava.c6BeingLazy;

import java.util.function.Supplier;

/***
 * Adding a Level of Indirection
 * We are going to use the Supplier<T> class. This is a functional interface in the JDK,
 * with one abstract method named get() that returns an instance. In other words, this is
 * a factory that keeps on giving without expecting anything as input.
 * In the most rudimentary form a Supplier will return an instance, like that:
 *      Supplier<Heavy> supplier = () -> new Heavy();
 * Alternatively, we could use a constructor reference, like this:
 *      Supplier<Heavy> supplier = Heavy::new;
 * We looked at what supplier can do for us, but we need something more than this simple
 * form. We need to postpone and cache the instance. We can do that by moving the instance
 * creation to another function.
 */
public class P2Holder {

    private Supplier<Heavy> heavySupplier = () -> createAndCacheHeavy();

    public P2Holder() {
        System.out.println("Holder created");
    }

    public Heavy getHeavy() {
        return heavySupplier.get();
    }

    //...

    /*
    We are marking this method synchronized so threads calling this method concurrently
    will be mutually exclusive. But within this method, on the first call we quickly
    replace the Supplier reference heavy with a direct supplier, HeavyFactory, that will
    return an instance of Heavy.
     */
    private synchronized Heavy createAndCacheHeavy() {

        class HeavyFactory implements Supplier<Heavy> {

            private final Heavy heavyInstance = new Heavy();

            @Override
            public Heavy get() {
                return heavyInstance;
            }
        }

        if (!(heavySupplier instanceof HeavyFactory)) {
            heavySupplier = new HeavyFactory();
        }

        return heavySupplier.get();
    }

    public static void main(String[] args) {

        final P2Holder holder = new P2Holder();
        System.out.println("deferring heavy creation...");
        System.out.println(holder.getHeavy());
        System.out.println(holder.getHeavy());

        /*
        We designed lazy initialization and, at the same time avoided, null checks. Furthermore,
        we ensured the thread safety of the lazy instance creation. This is a simple, lightweight
        implementation of the "virtual proxy" pattern.
         */

    }
}
