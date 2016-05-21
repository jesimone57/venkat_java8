package fpij.collections;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.join;

/**
 * Created by jsimone on 12/10/15.
 */
public class PrintList {

    static final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {

        System.out.println(join(", ", friends));

        System.out.println( friends.stream().map(String::toUpperCase).collect(Collectors.joining(", ")));

    }
}
