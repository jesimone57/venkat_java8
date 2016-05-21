package fpij.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by jsimone on 12/9/15.
 */
public class PickAnElementElegant {

    public static void main(String[] args) {

        final List<String> friends  = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        pickName(friends, "N");
        pickName(friends, "Q");

    }

    public static void pickName(final List<String> names, final String startingLetter) {
        final Optional<String> foundName = names.stream()
                .filter(name ->name.startsWith(startingLetter))
                .findFirst();
        System.out.println(String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));

        foundName.ifPresent(name -> System.out.println("Hello " + name));
    }


}
