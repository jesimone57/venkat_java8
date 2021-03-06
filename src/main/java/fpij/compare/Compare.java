package fpij.compare;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;


/**
 * Created by jsimone on 12/10/15.
 */
public class Compare {

    public static void printPeople(final String message, final List<Person> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }

    public static void main(String[] args) {
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35));

        List<Person> ascendingAge = people.stream()
                //.sorted((person1, person2) -> person1.ageDifference(person2))    // lambda expression
                .sorted(Person::ageDifference)      // method reference compiler implies person1 is target and person2 is argument
                .collect(toList());
        printPeople("Sorted in ascending order of age: ", ascendingAge);


        printPeople("Sorted in descending order of age: ", people.stream()
                .sorted((person1, person2) -> person2.ageDifference(person1))
                .collect(toList()));


        Comparator<Person> compareAscending = (person1, person2) -> person1.ageDifference(person2);
        Comparator<Person> compareDescending = compareAscending.reversed();

        printPeople("Sorted in ascending order of age: ", people.stream()
                        .sorted(compareAscending)
                        .collect(toList())
        );
        printPeople("Sorted in descending order of age: ", people.stream()
                        .sorted(compareDescending)
                        .collect(toList())
        );



        printPeople("Sorted in ascending order of name: ", people.stream()
                .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
                .collect(toList()));


        // NOTE:  The min() method returns an Optional because the list may be empty and therefore there may not be a youngest person.
        people.stream()
                .min(Person::ageDifference)
                .ifPresent(youngest -> System.out.println("Youngest: " + youngest));

        // NOTE:  The max() method returns an Optional because the list may be empty and therefore there may not be an eldest person.
        people.stream()
                .max(Person::ageDifference)
                .ifPresent(eldest -> System.out.println("Eldest: " + eldest));

        // comparing by name
        final Function<Person, String> byName = person -> person.getName();
        people.stream().sorted(comparing(byName));


        // comparing by name and age
        final Function<Person, Integer> byAge = person -> person.getAge();
        final Function<Person, String> byTheirName = person -> person.getName();
        printPeople("Sorted in ascending order of age and name: ", people.stream()
                .sorted(comparing(byAge).thenComparing(byTheirName))
                .collect(toList()));
    }
}
