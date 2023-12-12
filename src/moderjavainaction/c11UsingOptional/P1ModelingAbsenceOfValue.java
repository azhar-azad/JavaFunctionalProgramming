package moderjavainaction.c11UsingOptional;

import moderjavainaction.c11UsingOptional.traditionalmodel.Car;
import moderjavainaction.c11UsingOptional.traditionalmodel.Insurance;
import moderjavainaction.c11UsingOptional.traditionalmodel.Person;

/***
 * How do you model the absence of a value
 * See Person, Car and Insurance classes and imagine what's problematic with the following code:
 *      public String getCarInsuranceName(Person person) {
 *          return person.getCar().getInsurance().getName();
 *      }
 */
public class P1ModelingAbsenceOfValue {

    public static void main(String[] args) {

        /*
        Reducing NullPointerExceptions with defensive checking
        Typically, you can add null checks where necessary and often with different styles. A first attempt to write a
        method preventing a NullPointerException is shown below.
        See getCarInsuranceName method.
        Here, each null check increases the nesting level of the remaining part of the invocation chain.
         */
        /*
        We can attempt another solution by doing something different as shown.
        See getCarInsuranceName2 method.
        Here, each null check adds an exit point.
         */

    }

    public static String getCarInsuranceName(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    public static String getCarInsuranceName2(Person person) {
        if (person == null) {
            return "Unknown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }
}
