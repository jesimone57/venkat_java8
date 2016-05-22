package fpij.collections;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by jsimone on 12/9/15.
 */
public class PickDifferentNames {

    // filter takes a Predicate object so we define one here:
    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }

    public static void main(String[] args) {

        final List<String> friends  = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");
        final List<String> editors  = Arrays.asList("Brian", "Jackie", "John", "Mike");

        final Predicate<String> startsWithN = name -> name.startsWith("N");
        final Predicate<String> startsWithB = name -> name.startsWith("B");

        final long countFriendsStartN = friends.stream().filter(startsWithN).count();
        final long countFriendsStartB = friends.stream().filter(startsWithB).count();


        //better, no repetition using a defined predicate
        final long count2FriendsStartN = friends.stream().filter(checkIfStartsWith("N")).count();
        final long countF2riendsStartB = friends.stream().filter(checkIfStartsWith("B")).count();

        // best, no repetition using a defined function
        // even more concise function definition
        final Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.startsWith(letter);

        final long count3FriendsStartN = friends.stream().filter(startsWithLetter.apply("N")).count();
        final long countF3riendsStartB = friends.stream().filter(startsWithLetter.apply("B")).count();
    }

}
