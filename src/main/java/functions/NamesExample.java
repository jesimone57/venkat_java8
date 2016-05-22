package functions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by jsimone on 5/22/16.
 * see http://howtodoinjava.com/java-8/how-to-use-predicate-in-java-8/
 */
public class NamesExample {

	// tedious definition of lenFunc
	// takes a string from the stream and returns an int
	public static Function<String, Integer> lenFunc1() {
		return new Function<String, Integer>() {
			@Override
			public Integer apply(String s) {
				return s.length();
			}
		};
	}

	// more concise definition of lenFunc
	// takes a string from the stream and returns an int
	final static Function<String, Integer> lenFunc2 = s -> s.length();

	final static Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.startsWith(letter);

	// more fancy function which takes a integer and returns a predicate
	final static Function<Integer, Predicate<String>> isLen = len -> (name -> name.length() == len);

	// predicate for length 4 strings
	final static Predicate<String> len4Pred = name -> name.length() == 4;

	// predicate for length N strings.  Note:  string comes from stream and len comes from pred argument
	final static Predicate<String> isOfLength(final Integer len) {
		return name -> name.length() == len;
	}

	// predicate for length N strings.  Note:  string comes from stream and len comes from pred argument
	final static Predicate<String> isNotOfLength(final Integer len) {
		return name -> name.length() != len;
	}

	// more fancy function which takes a boolean and uses it to apply to a predicate it returns
	final static Function<Boolean, Predicate<String>> isLen4 = bool -> (name -> (name.length() == 4) == bool);


	public static void main(String[] args) {

		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott",
				"Bill", "Randy", "Sam", "Doris", "Robert", "Alice", "Fred");

		System.out.println("\nNames ...");
		System.out.println(friends.stream().collect(Collectors.joining(" ")));

		System.out.println("\nlengths of each name using function ...");
		List<Integer> lengths = friends.stream().map(lenFunc1()).collect(Collectors.toList());
		System.out.println(lengths);

		System.out.println("\nlengths of each name using function defined by lambda expression...");
		List<Integer> lengths2 = friends.stream().map(lenFunc2).collect(Collectors.toList());
		System.out.println(lengths2);

		System.out.println("\nNames that start with N ...");
		List<String> friendsStartN = friends.stream().filter(startsWithLetter.apply("N")).collect(Collectors.toList());
		System.out.println(friendsStartN);

		System.out.println("\nNames of length 4 using predicate ...");
		List<String> length4Strs1 = friends.stream().filter(len4Pred).collect(Collectors.toList());
		System.out.println(length4Strs1);

		System.out.println("\nNames of length 4 using function returning predicate ...");
		List<String> length4Strs2 = friends.stream().filter(isLen.apply(4)).collect(Collectors.toList());
		System.out.println(length4Strs2);

		System.out.println("\nNames of length 4 another way to do it ...");
		List<String> length4Strs3 = friends.stream().filter(isLen4.apply(true)).collect(Collectors.toList());
		System.out.println(length4Strs3);

		System.out.println("\nNames of length 4 using a predicate which takes an arg ...");
		List<String> length4Strs4 = friends.stream().filter(isOfLength(4)).collect(Collectors.toList());
		System.out.println(length4Strs4);

		System.out.println("\nNames not of length 4 ...");
		List<String> notlength4Strs1 = friends.stream().filter(isLen4.apply(false)).collect(Collectors.toList());
		System.out.println(notlength4Strs1);

		System.out.println("\nNames not of length 4 ...");
		List<String> notlength4Strs2 = friends.stream().filter(isNotOfLength(4)).collect(Collectors.toList());
		System.out.println(notlength4Strs2);
	}

}
