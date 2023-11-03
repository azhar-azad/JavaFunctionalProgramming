package functionalpatterns.factory.traditionalway;

import functionalpatterns.factory.*;

public class VehicleFactory {

    /*
    Before Java introduced functional programming, this pattern could be
    implemented just by using if conditions, a switch case or even a Enum.
    Let's take a look at an implementation of this pattern in a non-functional
    way.
     */
    public static Vehicle instanceOfType(VehicleType type, VehicleColor color) {

        if (type.equals(VehicleType.CAR)) {
            return new Car(color);
        } else if (type.equals(VehicleType.BUS)) {
            return new Bus(color);
        } else if (type.equals(VehicleType.TRUCK)) {
            return new Truck(color);
        } else {
            throw new IllegalArgumentException("No support for type: " + type);
        }
    }
    /*
    As we can see, our Factory method accepts the type that we want to
    instantiate and also a "vehicle color", which will be used to instantiate
    a vehicle of that color.
     */
}
