package fpij.collections;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jsimone on 12/8/15.
 */
public class DoubleAndTotal {

    static final List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

    public static void main(String[] args) {

        // imperative style
        int total = 0;
        for (Integer num: nums) {
            total += num * 2;
        }
        System.out.println(total);

        // functional style. map/reduce pattern
        System.out.println( nums.stream()
                .map(i -> i * 2)
                .reduce(0, (carry, elem) -> carry + elem));

        // using sum
        System.out.println( nums.stream()
                .mapToInt(Integer::intValue)
                .map(i -> i * 2)
                .sum());
    }
}
