package fpij.collections;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by jsimone on 12/9/15.
 */
public class PickElementsMultipleCollection {
    public static void main(String[] args) {

        final List<String> friends =
                Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        final List<String> comrades =
                Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");
        final List<String> editors =
                Arrays.asList("Brian", "Jackie", "John", "Mike");

        final long countFriendsStartN = friends.stream().filter(name -> name.startsWith("N")).count();
        final long countComradesStartN = comrades.stream().filter(name -> name.startsWith("N")).count();
        final long countEditorsStartN = editors.stream().filter(name -> name.startsWith("N")).count();


        // using a predicate
        final Predicate<String> startsWithN = name -> name.startsWith("N");
        final long count2FriendsStartN = friends.stream().filter(startsWithN).count();
        final long count2ComradesStartN = comrades.stream().filter(startsWithN).count();
        final long count2EditorsStartN = editors.stream().filter(startsWithN).count();
    }
}
