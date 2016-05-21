package introduction.fpij;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

/**
 * Created by jsimone on 12/9/15.
 *
 * Ref:  http://www.deadcoderising.com/2015-05-19-java-8-replace-traditional-for-loops-with-intstreams/
 *
 * Other good ref:   http://www.leveluplunch.com/java/examples/java-util-stream-intstream-example/
 *
 */
public class IntStreamExamples {

    public static void main(final String[] args) {

        range(2, 100).forEach(System.out::println);

        // odds
        range(3, 101).filter(n -> n % 2 != 0).forEach(System.out::println);

        // evens
        range(3, 101).filter(n -> n % 2 == 0).forEach(System.out::println);

        // odds
        // NOTE:   Now, if you just want to convert an IntStream to a Stream<Integer>,
        //  there's a dedicated function for this job called boxed.
        final List<Integer> odds = range(3, 101).filter(n -> n % 2 != 0)
                .boxed().collect(Collectors.toList());

        System.out.println("Odds: "+ odds);

        // evens
        final List<Integer> evens = range(3, 101).filter(n -> n % 2 == 0)
                .boxed().collect(Collectors.toList());

        System.out.println("Evens: "+ evens);
    }
}
