package functions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by jsimone on 5/22/16.
 * see http://www.leveluplunch.com/java/tutorials/007-sort-arraylist-stream-of-objects-in-java8/
 */
public class PersonsExample {
	final static List<Person> persons = Arrays.asList(

			new Person("Bill Smith", 42, Dept.FINANCE, "2002-05-08", Gender.M),
			new Person("Tom Jones", 30, Dept.FINANCE, "2013-03-27", Gender.M),
			new Person("Bill Manley", 33, Dept.FINANCE, "2015-08-08", Gender.M),

			new Person("Sally Jones", 38, Dept.HUMAN_RESOURCES, "1998-03-17", Gender.F),
			new Person("Bo Derek", 60, Dept.HUMAN_RESOURCES, "1996-03-20", Gender.F),
			new Person("Fran Black", 42, Dept.HUMAN_RESOURCES, "2006-09-09", Gender.F),

			new Person("Sam Stone", 59, Dept.DEVELOPMENT, "2008-05-14", Gender.M),
			new Person("Betty Davies", 29, Dept.DEVELOPMENT, "2010-06-14", Gender.F),
			new Person("Rob Parker", 32, Dept.DEVELOPMENT, "2010-10-10", Gender.M),

			new Person("Tim Richards", 25, Dept.SALES, "2006-11-03", Gender.M),
			new Person("Tom Roberts", 29, Dept.SALES, "2012-11-03", Gender.M),
			new Person("Meg Ryan", 21, Dept.SALES, "2002-11-03", Gender.F),

			new Person("Alice Jones", 32, Dept.MARKETING, "2009-01-03", Gender.F),
			new Person("Alice Smith", 35, Dept.MARKETING, "2010-01-10", Gender.F),
			new Person("Tina Jones", 37, Dept.MARKETING, "2011-06-05", Gender.F),
			new Person("Tina Smith", 39, Dept.MARKETING, "2012-09-22", Gender.F),

			new Person("Joe Davis", 40, Dept.RESEARCH, "1999-05-14", Gender.M),
			new Person("Bob Brown", 42, Dept.RESEARCH, "2005-07-14", Gender.M),
			new Person("Ed Lee", 44, Dept.RESEARCH, "2008-06-14", Gender.M),
			new Person("Sue White", 47, Dept.RESEARCH, "2011-05-14", Gender.F)
	);

	// pre java 8 comparator definition
	final static Comparator<Person> compareByName = new Comparator<Person>() {
		public int compare(Person p1, Person p2) {
			return p1.getName().compareToIgnoreCase(p2.getName());
		}
	};

	/**
	 * see http://www.mkyong.com/java8/java-8-lambda-comparator-example/
	 * using java 8 lambda expressions
	 */
	final static Comparator<Person> byName = (Person o1, Person o2) -> o1.getName().compareTo(o2.getName());
	final static Comparator<Person> byAge = (Person o1, Person o2) -> o1.getAge().compareTo(o2.getAge());
	final static Comparator<Person> byGender = (Person o1, Person o2) -> o1.getGender().compareTo(o2.getGender());
	final static Comparator<Person> byHireDate = (Person o1, Person o2) -> o1.getHireDate().compareTo(o2.getHireDate());
	final static Comparator<Person> byDept = (Person o1, Person o2) -> o1.getDept().compareTo(o2.getDept());


	final static Predicate<Person> isAgeInRange(int start, int end) {
		return person -> person.getAge() >= start && person.getAge() <= end;
	}

	final static Predicate<Person> isHiredBefore(String date) {
		return person -> person.getHireDate().isBefore(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}

	final static Predicate<Person> isMale = p -> p.getGender() == Gender.M;


	public static void main(String[] args) {
		System.out.println("\nList of all persons ...");
		persons.forEach(System.out::println);

		System.out.println("\nList of all persons in age bracket 30 to 45...");
		List<Person> persons30To45 = persons.stream().filter(isAgeInRange(30, 45)).collect(Collectors.toList());
		persons30To45.stream().sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())).forEach(System.out::println);

		System.out.println("\nList of all male persons...");
		List<Person> personsMale = persons.stream().filter(isMale).collect(Collectors.toList());

		System.out.println("\nSorted by natural sort order age...");
		personsMale.stream().sorted().forEach(System.out::println);
		System.out.println("\nSorted by name...");
		personsMale.stream().sorted(byName).forEach(System.out::println);
		System.out.println("\nSorted by hireDate...");
		personsMale.stream().sorted(byHireDate).forEach(System.out::println);
		System.out.println("\nSorted by name, then dept ...");
		personsMale.stream().sorted(byName).sorted(byDept).forEach(System.out::println);

		System.out.println("\nAll Sorted by gender reversed ...");
		persons.stream().sorted(byGender.reversed()).forEach(System.out::println);

		System.out.println("\nAll hired befe 2006-01-01 ...");
		persons.stream().filter(isHiredBefore("2006-01-01")).sorted(byHireDate).forEach(System.out::println);

		System.out.println("\nAverage of all ages ...");
		System.out.println(persons.stream().mapToInt(p -> p.getAge()).average());
	}
}
