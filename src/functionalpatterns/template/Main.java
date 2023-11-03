package functionalpatterns.template;

import functionalpatterns.factory.VehicleColor;
import functionalpatterns.factory.functionalway.VehicleTypeFunctional;
import functionalpatterns.template.functionalway.VehicleFunctional;
import functionalpatterns.template.traditionalway.AbstractVehicle;
import functionalpatterns.template.traditionalway.Bus;
import functionalpatterns.template.traditionalway.Vehicle;

/***
 * Template Method pattern
 * Template method is a pattern that allows us to define some common steps for
 * an algorithm and then the subclasses override some of these steps with their
 * specific behaviours for a particular step.
 */
public class Main {

    /*
    How did we use to write this pattern in the "old or traditional" way in Java?
    Well, most of the time we had to use an abstract class and define abstract
    methods for each sub-step that could be overridden.
    See AbstractVehicle in the "traditionalway" package.
     */


    /*
    So what's the alternative? Let's write another interface for Vehicle and
    we'll make use of the default modifier to provide a default implementation
    of our method in the interface.
    See VehicleFunctional interface.
    Let's use the new interface.
     */
    
}
