package venkatfpij.c6beinglazy;

/***
 * A Familiar Approach
 * We are going to craft a way to delay the creation of a heavyweight instance
 * with a familiar approach.
 * We are initializing the heavy object when it is called, instead of crating
 * this on the class constructor, thus doing lazy initialization.
 */
public class P1HolderNaive {

    private Heavy heavy;

    public P1HolderNaive() {
        System.out.println("Holder created");
    }

    /*
    Without synchronized:
    For an instance of HolderNaive, the dependent instance of Heavy is created
    on the first call to the getHeavy() method. On subsequent calls to this
    method, the already created instance will be returned. That's exactly what
    we want, but there's a catch. This code suffers from a race condition. If
    two or more threads call the getHeavy() method at the same time, then we
    could end up with multiple Heavy instances, potentially one per thread.

    Providing Thread Safety
    With synchronized:
    We mark getHeavy() with the synchronized keyword to ensure exclusion. If
    two or more threads call this method concurrently, due to mutual exclusion
    only one will be allowed to enter and the others will queue up for their turn.
    The first one to enter into the method will create the instance. When subsequent
    threads enter this method they will see that the instance already exists, and
    will simply return it.
     */
    public synchronized Heavy getHeavy() {
        if (heavy == null) {
            heavy = new Heavy();
        }

        return heavy;
    }

    //...

    public static void main(String[] args) {

        final P1HolderNaive holder = new P1HolderNaive();
        System.out.println("deferring heavy creation...");
        System.out.println(holder.getHeavy());
        System.out.println(holder.getHeavy());

        /*
        This appears to work. The solution is familiar, but it's also a rather simplistic solution
        that fails thread safety.
        By using synchronized we averted the race condition, but the solution created another
        negative impact. Every call to the getHeavy() method now has to endure the synchronization
        overhead; the calling threads have to cross the memory barrier even there are no
        concurrently competing threads.
         */
    }
}
