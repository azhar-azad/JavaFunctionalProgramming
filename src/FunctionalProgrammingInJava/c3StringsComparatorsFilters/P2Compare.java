package FunctionalProgrammingInJava.c3StringsComparatorsFilters;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/***
 * Sorting with a Comparator
 * Reusing a Comparator
 * Multiple and Fluent Comparisons
 */
public class P2Compare {

    public static void main(String[] args) {
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35));

        /*
        Let's first sort the people in the list in ascending order by age.
        We can get a Stream from the list and conveniently call the sorted() method on it. Rather than messing
        with the given collection, it will return a sorted collection. We can nicely configure the Comparator
        parameter when calling this method.
        We invoked the sorted() method on the Stream of a list of people. This method takes a Comparator as
        its parameter. Since Comparator is a functional interface, we conveniently passed in a lambda expression.
         */
        List<Person> ascendingAge = people.stream()
                .sorted((person1, person2) -> person1.ageDifference(person2))
                .toList();
        printPeople("Sorted in ascending order by age: ", ascendingAge);

        /*
        Rather than using the lambda expression, we can use the method reference.
         */
        List<Person> ascendingAge2 = people.stream()
                .sorted(Person::ageDifference)
                .toList();
        printPeople("Sorted in ascending order by age again: ", ascendingAge2);

        /*
        Let's sort them in descending order.
        The difference is in the implementation of the lambda expression - we switched the people
        in the age comparison.
        We can't refactor this version to use the method reference, though, because the parameter order
        here doesn't follow the parameter-routing conventions for method reference; the first parameter
        is not used as a target to the method, but rather than its argument. There's a way to fix that.
         */
        printPeople("Sorted in descending order by age: ", people.stream()
                .sorted((people1, people2) -> people2.ageDifference(people1))
                .toList());

        /*
        If all we want is a reverse of the comparison, the JDK has us covered with a reversed() method
        on the Comparator, marked with a special method modifier called default.
         */
        Comparator<Person> compareAscending = (person1, person2) -> person1.ageDifference(person2);
        Comparator<Person> compareDescending = compareAscending.reversed();
        printPeople("Sorted in ascending order by age: ", people.stream()
                .sorted(compareAscending).collect(Collectors.toList()));
        printPeople("Sorted in descending order by age: ", people.stream()
                .sorted(compareDescending).collect(Collectors.toList()));

        /*
        Let's sort in ascending alphabetical order by name; again, only the logic with the lambda expression
        needs to change.
         */
        printPeople("Sorted in ascending order by name: ", people.stream()
                .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
                .toList());

        /*
        Let's pick the youngest person in the list.
         */
        people.stream()
                .min(Person::ageDifference)
                .ifPresent(youngest -> System.out.println("Youngest: " + youngest));

        /*
        Let's pick the oldest person in the list.
         */
        people.stream()
                .max(Person::ageDifference)
                .ifPresent(eldest -> System.out.println("Eldest: " + eldest));

        /*
        To sort people by their name we used compareTo method. But we can do better thanks to
        convenience functions in the Comparator interface.
        The comparing() method uses the logic embedded in the provided lambda expression to create a Comparator.
        In other words, it's a higher-order function that takes in one function (Function)
        and returns another (Comparator).
         */
        final Function<Person, String> funcToGetName = Person::getName;
        printPeople("Sorted by name using Function: ", people.stream()
                .sorted(Comparator.comparing(funcToGetName)).toList());

        /*
        To make multiple comparison like sorting people in ascending order by both age and name,
        here is some cogent syntax.
        The comparing() method created and returned a Comparator to compare based on age. On the
        returned Comparator we invoked the thenComparing() method to create a composite comparator
        that compares based on both age and name.
         */
        final Function<Person, Integer> byAge = Person::getAge;
        final Function<Person, String> byName = Person::getName;

        printPeople("Sorted in ascending order by age and name: ", people.stream()
                .sorted(Comparator.comparing(byAge)
                        .thenComparing(Comparator.comparing(byName)))
                .toList());

    }

    public static void printPeople(final String message, final List<Person> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }
}
