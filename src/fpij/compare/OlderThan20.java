package fpij.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

/**
 * Created by jsimone on 12/11/15.
 *
 * Functional Programming in Java :  Chapter 3,  page 52
 */
public class OlderThan20 {

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


        List<Person> olderThan20 = new ArrayList<>();
        people.stream()
                .filter(person -> person.getAge() > 20)
                .forEach(person -> olderThan20.add(person));
        System.out.println("People older than 20: " + olderThan20);


        List<Person> older2Than20 = people.stream()
                .filter(person -> person.getAge() > 20)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("People older than 20: " + older2Than20);


        List<Person> older3Than20 = people.stream()
                .filter(person -> person.getAge() > 20)
                .collect(toList());
        System.out.println("People older than 20: " + older3Than20);


        Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect(groupingBy(Person::getAge)); // note groupingBy argument is the key of the map
        System.out.println("People grouped by age: " + peopleByAge);


        Map<Integer, List<String>> nameOfPeopleByAge = people.stream()
                .collect(groupingBy(Person::getAge, mapping(Person::getName, toList())));  // here key and value are supplied
        System.out.println("People grouped by age: " + nameOfPeopleByAge);


        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonInEachAlphabet =
                people.stream()
                        .collect(groupingBy(person -> person.getName().charAt(0), reducing(BinaryOperator.maxBy(byAge))));
        System.out.println("Oldest person in each alphabet:"); System.out.println(oldestPersonInEachAlphabet);
    }
}
