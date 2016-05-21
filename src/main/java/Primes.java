import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Sample code given by Venkat Subramanian at RichWeb 2015 key note presentation
 * NOTE:  Imperitvie style solution is commented out
 */

public class Primes {

    public static boolean isPrime(int number) {
        // boolean divisible = false;
        //
        // for(int i = 2; i < number; i++) {
        //   if(number % i == 0) {
        //     divisible = true;
        //     break;
        //   }
        // }
        //
        // return number > 1 && ! divisible;

        IntPredicate isDivisible = divisor -> number % divisor == 0;
        //Predicate<Integer> isDivisible2 = divisor -> number % divisor == 0;

        return number > 1 &&
                IntStream.range(2, number)
                        .noneMatch(isDivisible);
    }



    public static double compute(int start, int n) {
        // double result = 0;
        // int index = start;
        // int count = 0;
        //
        // while(count < n) {
        //   if(isPrime(index)) {
        //     result += Math.sqrt(index);
        //     count++;
        //   }
        //   index++;
        // }
        //
        // return result;

        return Stream.iterate(start, e -> e + 1)
                .filter(Primes::isPrime)   // Primes::isPrime is short-hand for x -> Primes.isPrime(x)  ... this is called a method reference
                .mapToDouble(Math::sqrt)
                .limit(n)
                .sum();
    }

    public static void main(String[] args) throws Exception {
        //compute the total of sqrt of n prime numbers starting with k

        System.out.println(compute(51, 101));
    }
}
