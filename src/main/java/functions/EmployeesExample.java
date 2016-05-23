package functions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by jsimone on 5/22/16.
 * see http://www.leveluplunch.com/java/tutorials/007-sort-arraylist-stream-of-objects-in-java8/
 */
public class EmployeesExample {
	final static List<Employee> employees = Arrays.asList(

			new Employee("Bill Smith", 42, Dept.FINANCE, "2002-05-08", Gender.M),
			new Employee("Tom Jones", 30, Dept.FINANCE, "2013-03-27", Gender.M),
			new Employee("Bill Manley", 33, Dept.FINANCE, "2015-08-08", Gender.M),

			new Employee("Sally Jones", 38, Dept.HUMAN_RESOURCES, "1998-03-17", Gender.F),
			new Employee("Bo Derek", 60, Dept.HUMAN_RESOURCES, "1996-03-20", Gender.F),
			new Employee("Fran Black", 42, Dept.HUMAN_RESOURCES, "2006-09-09", Gender.F),

			new Employee("Sam Stone", 59, Dept.DEVELOPMENT, "2008-05-14", Gender.M),
			new Employee("Betty Davies", 29, Dept.DEVELOPMENT, "2010-06-14", Gender.F),
			new Employee("Rob Parker", 32, Dept.DEVELOPMENT, "2010-10-10", Gender.M),

			new Employee("Tim Richards", 25, Dept.SALES, "2006-11-03", Gender.M),
			new Employee("Tom Roberts", 29, Dept.SALES, "2012-11-03", Gender.M),
			new Employee("Meg Ryan", 21, Dept.SALES, "2002-11-03", Gender.F),

			new Employee("Alice Jones", 32, Dept.MARKETING, "2009-01-03", Gender.F),
			new Employee("Alice Smith", 35, Dept.MARKETING, "2010-01-10", Gender.F),
			new Employee("Tina Jones", 37, Dept.MARKETING, "2011-06-05", Gender.F),
			new Employee("Tina Smith", 39, Dept.MARKETING, "2012-09-22", Gender.F),

			new Employee("Joe Davis", 40, Dept.RESEARCH, "1999-05-14", Gender.M),
			new Employee("Bob Brown", 42, Dept.RESEARCH, "2005-07-14", Gender.M),
			new Employee("Ed Lee", 44, Dept.RESEARCH, "2008-06-14", Gender.M),
			new Employee("Sue White", 47, Dept.RESEARCH, "2011-05-14", Gender.F)
	);

	// pre java 8 comparator definition
	final static Comparator<Employee> compareByName = new Comparator<Employee>() {
		public int compare(Employee p1, Employee p2) {
			return p1.getName().compareToIgnoreCase(p2.getName());
		}
	};

	/**
	 * see http://www.mkyong.com/java8/java-8-lambda-comparator-example/
	 * using java 8 lambda expressions
	 */
	final static Comparator<Employee> byName = (Employee o1, Employee o2) -> o1.getName().compareTo(o2.getName());
	final static Comparator<Employee> byAge = (Employee o1, Employee o2) -> o1.getAge().compareTo(o2.getAge());
	final static Comparator<Employee> byGender = (Employee o1, Employee o2) -> o1.getGender().compareTo(o2.getGender());
	final static Comparator<Employee> byHireDate = (Employee o1, Employee o2) -> o1.getHireDate().compareTo(o2.getHireDate());
	final static Comparator<Employee> byDept = (Employee o1, Employee o2) -> o1.getDept().compareTo(o2.getDept());

	final static Function<Employee, String> byName2 = person -> person.getName();
	final static Function<Employee, Integer> byAge2 = person -> person.getAge();
	final static Function<Employee, Gender> byGender2 = person -> person.getGender();
	final static Function<Employee, LocalDate> byHireDate2 = person -> person.getHireDate();
	final static Function<Employee, Dept> byDept2 = person -> person.getDept();


	final static Predicate<Employee> isAgeInRange(int start, int end) {
		return person -> person.getAge() >= start && person.getAge() <= end;
	}

	final static Predicate<Employee> isHiredBefore(String date) {
		return person -> person.getHireDate().isBefore(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}

	final static Predicate<Employee> isMale = p -> p.getGender() == Gender.M;


	public static void main(String[] args) {
		System.out.println("\nList of all employees ...");
		employees.forEach(System.out::println);

		System.out.println("\nList of all employees in age bracket 30 to 45...");
		List<Employee> employees30To45 = employees.stream().filter(isAgeInRange(30, 45)).collect(Collectors.toList());
		employees30To45.stream().sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())).forEach(System.out::println);

		System.out.println("\nList of all male employees...");
		List<Employee> employeesMale = employees.stream().filter(isMale).collect(Collectors.toList());

		System.out.println("\nSorted by natural sort order age...");
		employeesMale.stream().sorted().forEach(System.out::println);
		System.out.println("\nSorted by name...");
		employeesMale.stream().sorted(byName).forEach(System.out::println);
		System.out.println("\nSorted by hireDate...");
		employeesMale.stream().sorted(byHireDate).forEach(System.out::println);

		// in order to sort on more than 1 field we have to resort to something different than .sorted()
		System.out.println("\nSorted by dept, then name ...");
		employeesMale.stream().sorted(comparing(byDept2).thenComparing(byName2)).forEach(System.out::println);

		System.out.println("\nAll Sorted by gender reversed ...");
		employees.stream().sorted(byGender.reversed()).forEach(System.out::println);

		System.out.println("\nAll hired before 2006-01-01 ...");
		employees.stream().filter(isHiredBefore("2006-01-01")).sorted(byHireDate).forEach(System.out::println);

		System.out.println("\nAverage of all ages ...");
		System.out.println(employees.stream().mapToInt(p -> p.getAge()).average());
	}
}
