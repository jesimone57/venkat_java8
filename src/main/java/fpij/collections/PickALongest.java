package fpij.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by jsimone on 12/9/15.
 */
public class PickALongest {


    public static void main(String[] args) {

        final List<String> friends  = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        // optional result from reduce
        System.out.println("Total number of characters in all names: " + friends.stream()
                .mapToInt(name -> name.length())
                .reduce((len1, len2) -> len1 + len2));

        // result or default value returned from reduce
        // This version of reduce() does not return an Optional since if the collection is empty,
        // the default will be returned; there’s no concern of an absent or nonexistent value.
        System.out.println("Total number of characters in all names: " + friends.stream()
                .mapToInt(name -> name.length())
                .reduce(0, (len1, len2) -> len1 + len2));

        System.out.println("Total number of characters in all names: " + friends.stream()
                .mapToInt(name -> name.length())
                .sum());


        final Optional<String> aLongName = friends.stream()
                .reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2);

        aLongName.ifPresent(name -> System.out.println(String.format("A longest name: %s", name)));


        //If any name was longer than the given base, it would get picked up;
        // otherwise the function would return the base value, Steve in this example
        final String steveOrLonger = friends.stream()
                .reduce("Steve", (name1, name2) -> name1.length() >= name2.length() ? name1 : name2);

        System.out.println(String.format("Steve or longer than : %s", steveOrLonger));
    }
}
