package functionalpatterns.factory.functionalway;

import functionalpatterns.factory.Bus;
import functionalpatterns.factory.Car;
import functionalpatterns.factory.Truck;
import functionalpatterns.factory.Vehicle;
import functionalpatterns.factory.VehicleColor;

import java.util.function.Function;

/***
 * We are using a different VehicleType enum here, and we have added a factory
 * function to it. In our example, we pass the method references for each of
 * the constructors of our Vehicle implementations.
 * Also, every element in the enum is forced to implement its factory method, so
 * it's impossible that we forget adding a factory for a recently added type.
 * The Java compiler helps us here, guiding us to write the required changes
 * when a new type is added; no additional mental effort has to be done from
 * our side.
 */
public enum VehicleTypeFunctional {

    BUS(Bus::new),
    TRUCK(Truck::new),
    CAR(Car::new);

    public final Function<VehicleColor, Vehicle> factory;

    VehicleTypeFunctional(Function<VehicleColor, Vehicle> factory) {
        this.factory = factory;
    }
}
