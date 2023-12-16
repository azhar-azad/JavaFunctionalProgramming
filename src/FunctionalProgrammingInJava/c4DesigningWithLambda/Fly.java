package FunctionalProgrammingInJava.c4DesigningWithLambda;

/***
 * A Peek into the default Methods
 */
public interface Fly {

    default void takeOff() {
        System.out.println("Fly::takeOff");
    }

    default void land() {
        System.out.println("Fly::land");
    }

    default void turn() {
        System.out.println("Fly::turn");
    }

    default void cruise() {
        System.out.println("Fly::cruise");
    }
}
