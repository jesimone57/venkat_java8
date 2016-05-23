package fpij.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by jsimone on 12/9/15.
 */
public class PickElements {

	static final List<String> friends =
			Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

	public static void main(String[] args) {

		// imperitive style ...
		final List<String> startsWithN = new ArrayList<String>();
		for (String name : friends) {
			if (name.startsWith("N")) {
				startsWithN.add(name);
			}
		}
		System.out.println(startsWithN);

		friends.stream()
				.filter(name -> name.startsWith("N"))
				.forEach(System.out::println);

		// collect up the results into a list
		final List<String> anotherStartsWithN = friends.stream()
				.filter(name -> name.startsWith("N"))
				.collect(Collectors.toList());
		System.out.println(anotherStartsWithN);
		System.out.println(String.format("Found %d names that start with N", anotherStartsWithN.size()));

		// another example using a defined Predicate
		final Predicate<String> nameStartsWithS = name -> name.startsWith("S");

		final List<String> sFriends = friends.stream()
				.filter(nameStartsWithS)
				.collect(Collectors.toList());
		System.out.println(sFriends);
		System.out.println(String.format("Found %d names that start with S", sFriends.size()));
	}
}