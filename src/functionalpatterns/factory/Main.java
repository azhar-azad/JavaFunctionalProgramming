package functionalpatterns.factory;

import functionalpatterns.factory.functionalway.VehicleTypeFunctional;
import functionalpatterns.factory.traditionalway.VehicleFactory;

/***
 * Factory Method pattern
 * In the factory pattern we provide an interface to the client to create an
 * instance of the object, hiding the implementation details on how to create
 * the objects.
 */
public class Main {

    public static void main(String[] args) {

        /*
        Here's how we will use the traditional implementation in perspective of a
        hypothetical client:
         */
        Vehicle redCar =
                VehicleFactory.instanceOfType(functionalpatterns.factory.traditionalway.VehicleType.CAR, VehicleColor.RED);
        /*
        It doesn't look that bad but what are the issues with this implementation?
        - The use of if conditions to check type normally leads to code
        duplication.
        - Adding a new type implies having to remember that we have to add
        a new if condition.
        - We have to throw IllegalArgumentException, as it's possible that
        we forgot to handle a new type.
        - Harder to read than the functional approach.
        - Slightly more verbose that the functional approach.
         */

        /*
        Let's see now how can we use the functional implementation from a client.
         */
        Vehicle redCar2 = VehicleTypeFunctional.CAR.factory.apply(VehicleColor.RED);
    }
}
