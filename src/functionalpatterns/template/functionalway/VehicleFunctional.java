package functionalpatterns.template.functionalway;

import java.util.function.Consumer;

/***
 * As you can see, we now accept a Consumer to provide the preStartCheck()
 * functionalities to our class.
 */
public interface VehicleFunctional {

    default void start(Consumer<Void> preStartCheck) {
        preStartCheck.accept(null);
        System.out.printf("%s starting...5%n", this.getClass().getSimpleName());
    }
}
