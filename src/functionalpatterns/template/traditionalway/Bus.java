package functionalpatterns.template.traditionalway;

public class Bus extends AbstractVehicle {
    @Override
    void preStartCheck() {
        System.out.println("Check if every new passenger has paid for their tickets");
        System.out.println("Check if every passenger is seated");
    }
}
