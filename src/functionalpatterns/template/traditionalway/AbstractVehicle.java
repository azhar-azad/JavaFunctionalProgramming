package functionalpatterns.template.traditionalway;

/***
 * This is a very simple example, but basically each vehicle will print what
 * initial check it'll have to do before starting; in order to do that, each
 * subclass will have to override the preStartCheck() abstract method.
 * For example, if the Bus class has to extend the AbstractVehicle,
 * it'll force us to override the preStartCheck() method.
 * See the Bus class.
 *
 * This way is not ideal, as we have to create an abstract class and each of our
 * subclasses have to extend this abstract class.
 * It's messy, hard to follow and adds a considerable amount of clutter
 * considering what it actually does.
 */
public abstract class AbstractVehicle implements Vehicle {
    @Override
    public void start() {
        preStartCheck();
        System.out.printf("%s starting...%n", this.getClass().getSimpleName());
    }

    abstract void preStartCheck();
}
