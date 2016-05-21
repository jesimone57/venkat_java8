package introduction.fpij;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

/**
 * Created by jsimone on 12/16/15.
 */
public class Permute {
    public static void main(String[] args) {

        List<String> results = new ArrayList<>();
        permutation("", "01234567", results);

        System.out.println(results);
        System.out.println(results.size());
        System.out.println(8*7*6*5*4*3*2);
        System.out.println(factorial(8));

        List<String> l1 = Arrays.asList("tom", "dick", "harry");
        List<List<String>> perm1 = new ArrayList<>();
        permutation(new ArrayList<String>(), l1, perm1);
        System.out.println(perm1);

        List<Integer> l2 = Arrays.asList(0,1,2,3,4,5,6,7);
        List<List<Integer>> perm2 = new ArrayList<>();
        permutation(new ArrayList<Integer>(), l2, perm2);
        System.out.println(perm2);
    }

    private static void permutation(String prefix, String str, List<String> results) {
        int n = str.length();
        if (n == 0) {
            results.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), results);
            }
        }
    }

    public static long factorial(int n) {
        if (n > 20 || n < 0) throw new IllegalArgumentException(n + " is out of range");
        return LongStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
    }

    private static <T> void permutation(List<T> memory, List<T> input, List<List<T>> results) {
        int n = input.size();
        if (n == 0) {
            results.add(memory);
        } else {
            for (int i = 0; i < n; i++) {
                List<T> nextInput = new ArrayList();
                nextInput.addAll(input.subList(0, i));
                nextInput.addAll(input.subList(i + 1, n));

                List<T> nextMemory = new ArrayList();
                nextMemory.addAll(memory);
                nextMemory.add(input.get(i));
                permutation(nextMemory, nextInput, results);
            }
        }
    }
}
