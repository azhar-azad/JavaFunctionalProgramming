package moderjavainaction.c11UsingOptional.optionalmodel;

import java.util.Optional;

public class Person {
    private Optional<Car> car; // A person may not own a car, so you declare this field Optional
    public Optional<Car> getCar() {
        return car;
    }
}
