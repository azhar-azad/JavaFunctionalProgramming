package fpinjava.c3stringscomparatorsfilters;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/***
 * Using the collect Method and Collectors Class
 */
public class P3OlderThan20 {

    public static void main(String[] args) {

        /**
         * We want to collect only people older than 20 years from the original list.
         */
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35));

        /*
        Here's a version that uses mutability and forEach().
         */
        List<Person> olderThan20 = new ArrayList<>();
        people.stream()
                .filter(person -> person.getAge() > 20)
                .forEach(person -> olderThan20.add(person));
        System.out.println("People older than 20: " + olderThan20);

        /*
        The collect() method takes a stream of elements and collects or gathers them into a result container.
        To do that, the method needs to know three things:
        - How to make a result container (for example, using the ArrayList::new method).
        - How to add a single element to a result container (for example, using the ArrayList::add method).
        - How to merge one result container into another (for example, using the ArrayList::addAll method).
        Let's provide these operations to the collect() method to gather the results of a stream after a filter operation.
        The following version has many benefits over the previous version.
        - First, we're programming with intention and more expressively, clearly indicating our goal of collecting the
        result into an ArrayList. The collect() method took a factory or supplier as the first parameter, followed by
        operations that help accumulate elements into the collection.
        - Second, since we're not performing any explicit mutation in code, it's easy to parallelize the execution of
        the iteration. Since we let the library control the mutation, it can handle coordination and thread safety
        for us. This is in spite of the fact that ArrayList is not itself thread safe.
        - Third, the collect() method can perform parallel additions, as appropriate, into different sublists, and
        then merge them in a thread-safe manner into a larger list (hence the last parameter to help merge lists).
         */
        List<Person> olderThan20V2 = people.stream()
                .filter(person -> person.getAge() > 20)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("People older than 20: " + olderThan20V2);

        /*
        Let's look at another overloaded version of this method that's simpler and more convenient - it uses a Collector
        as the parameter. The Collectors utility class provides a toList() convenience method that creates an
        implementation of the Collector interface to accumulate elements into an ArrayList.
        There are several methods on the Collectors to perform various collect or accumulate operations. For example,
        in addition to toList(), there is toSet() to accumulate into a set, toMap() to gather into a key-value
        collection, and joining() to concatenate the elements into a string. We can also join multiple combine
        operations using methods like mapping(), collectingAndThen(), minBy(), maxBy() and groupingBy(). 
         */
        List<Person> olderThan20V3 = people.stream()
                .filter(person -> person.getAge() > 20)
                .collect(Collectors.toList());
        System.out.println("People older than 20: " + olderThan20V3);

        /*
        Let's use groupingBy() to group people by their age. The groupingBy() method takes a lambda expression or a
        method reference - called the classifier function - that returns the value of the property on which we want to
        do the grouping. The instances of Person will be grouped based on their age.
         */
        Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println("Grouping by age: " + peopleByAge);

        /*
        A variation of groupingBy() method can combine multiple criteria. The simple groupingBy collector uses the
        classifier to organize the stream of elements into buckets. The general groupingBy collector, on the other
        hand, can apply yet another collector to each bucket. In other words, downstream the values collected into
        buckets can go through more classification and collection.
        Continuing with the previous example, instead of creating a map of all Person objects by age, let's get only
        people's names, ordered by age.
        In this version groupingBy() takes two parameters: the first is the age, which is the criteria to group by,
        and the second is a Collector, which is the result of a call to the mapping() function. The mapping() method
        takes two details, the property on which to map (name in this case) and the type of the object to collect into,
        such as list or set.
         */
        Map<Integer, List<String>> nameOfPeopleByAge = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println("People grouped by age: " + nameOfPeopleByAge);

        /*
        Let's group the names by their first character and then get the oldest person in each group.
        We first group the names based on their first letter. For this, we pass a lambda expression as the first
        parameter to the groupingBy() method. From within the lambda expression we return the first character of the
        name for grouping purposes. The second parameter in this example, instead of mapping, performs a reduce
        operation. In each group, it reduces the elements to the oldest person, as decided by the maxBy() method.
        The syntax is a bit dense due to the combination of operations, but it reads like this: group by first
        character of name and reduce the person with maximum age.
         */
        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonOfEachLetter = people.stream()
                .collect(Collectors.groupingBy(
                        person -> person.getName().charAt(0),
                        Collectors.reducing(BinaryOperator.maxBy(byAge))));
        System.out.println("Oldest person of each letter:");
        System.out.println(oldestPersonOfEachLetter);
    }
}
