package moderjavainaction.c11UsingOptional.optionalmodel;

import java.util.Optional;

public class Car {
    private Optional<Insurance> insurance; // A car may not be insured, so you declare this field Optional
    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
