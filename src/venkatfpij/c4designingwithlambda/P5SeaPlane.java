package venkatfpij.c4designingwithlambda;

public class P5SeaPlane extends Vehicle implements FastFly, Sail {

    private int altitude;

    //...

    /*
    The Java compiler will force us to implement the cruise() method in
    the SeaPlane class because the default implementations in the interfaces
    FastFly and Sail conflict.

    When invoking the default methods from within the overriding method, it's
    required to use the super keyword. That's how the Java compiler knows if
    we're referring to a default method (when super is used) or a static method
    in the interface.
     */
    public void cruise() {
        System.out.print("SeaPlane::cruise currently cruise like: ");
        if (altitude > 0)
            FastFly.super.cruise();
        else
            Sail.super.cruise();
    }

    @Override
    public void takeOff() {
        FastFly.super.takeOff();
    }

    @Override
    public void land() {
        FastFly.super.land();
    }

    public static void main(String[] args) {

        P5SeaPlane seaPlane = new P5SeaPlane();

        /*
        The call to the takeOff() method should go to the implementation in the FastFly interface.
         */
        seaPlane.takeOff();

        /*
        The implementation of the turn() method in Vehicle should be picked for the call to the
        turn() method on the SeaPlane, even though these are available on the interfaces.
         */
        seaPlane.turn();

        /*
        Since we were forced to implement the cruise() method on the SeaPlane, that specific
        implementation of the method should be invoked for the call to cruise().
         */
        seaPlane.cruise();

        /*
        Finally, the call to the land() method will land on the implementation in the Fly interface.
         */
        seaPlane.land();
    }
}
