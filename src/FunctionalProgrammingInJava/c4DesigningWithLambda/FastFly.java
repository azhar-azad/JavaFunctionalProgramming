package FunctionalProgrammingInJava.c4DesigningWithLambda;

public interface FastFly extends Fly {

    default void takeOff() {
        System.out.println("FastFly::takeOff");
    }
}
