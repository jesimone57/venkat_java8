import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by jsimone on 12/16/15.
 *
 * Reference:  https://www.mathsisfun.com/numbers/pythagorean-triples.html
 *
 * extracted from http://www.erlang.org/doc/programming_examples/list_comprehensions.html
             Pythagorean triplets are sets of integers {A,B,C} such that A**2 + B**2 = C**2.

             The function pyth(N) generates a list of all integers {A,B,C} such that
                 A**2 + B**2 = C**2 and where the sum of the sides is equal to, or less than, N:

             pyth(N) ->
                     [ {A,B,C} ||
                     A <- lists:seq(1,N),
                     B <- lists:seq(1,N),
                     C <- lists:seq(1,N),
                     A+B+C =< N,
                     A*A+B*B == C*C
                     ].

             > pyth(3).
                    [].
             > pyth(11).
                    [].
             > pyth(12).
                    [{3,4,5},{4,3,5}]
             > pyth(50).
                     [{3,4,5},
                     {4,3,5},
                     {5,12,13},
                     {6,8,10},
                     {8,6,10},
                     {8,15,17},
                     {9,12,15},
                     {12,5,13},
                     {12,9,15},
                     {12,16,20},
                     {15,8,17},
                     {16,12,20}]

             The following code reduces the search space and is more efficient:

             pyth1(N) ->
                     [{A,B,C} ||
                     A <- lists:seq(1,N-2),
                     B <- lists:seq(A+1,N-1),
                     C <- lists:seq(B+1,N),
                     A+B+C =< N,
                     A*A+B*B == C*C ].

 */
public class PythagoreanTriples {

    public static String generatePythagoreanTriple(int m, int n) {
        if (m <= n) {
            throw new IllegalArgumentException("m must be greater than n");
        }
        //System.out.println(m+ " "+n);
        int a = m * m - n * n;
        int b = 2 * m * n;
        int c = m * m + n * n;

        return String.format("%d, %d, %d", a, b, c);
    }

    public static Stream<String> generatePythagoreanTriplesUpto(int m) {
        return IntStream.range(1, m)  // (1,2) -> 1,2, (1,3) -> 1,2,3, (1,4) -> 1,2,3,4, (1,5) -> 1,2,3,4,5
                .boxed()
                .map(n -> generatePythagoreanTriple(m, n));
    }

    public static void main(String[] args) {
        int maxNumberOfValues = 10;

        Stream.iterate(2, e -> e + 1)   // 2,3,4,5,6,7 ...
                .flatMap(PythagoreanTriples::generatePythagoreanTriplesUpto)
                .limit(maxNumberOfValues)
                .forEach(System.out::println);
    }

}
