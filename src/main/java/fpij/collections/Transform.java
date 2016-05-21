package fpij.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jsimone on 12/9/15.
 */
public class Transform {


    static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {

        // first attempt requires empty list and the effort to add elements to it
        final List<String> uppercaseNames = new ArrayList<String>();
        friends.forEach(name -> uppercaseNames.add(name.toUpperCase()));
        System.out.println(uppercaseNames);

        // stream is available for all collections
        friends.stream()
                .map(name -> name.toUpperCase())
                .forEach(name -> System.out.print(name + " "));

        System.out.println("\n");
        friends.stream()
                .map(name -> name.length())
                .forEach(len -> System.out.print(len + " "));

        // using method reference
        friends.stream()
                .map(String::toUpperCase)
                .forEach(name -> System.out.print(name + " "));

    }
}
