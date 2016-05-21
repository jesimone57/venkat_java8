package fpij.compare;

/**
 * Created by jsimone on 12/10/15.
 */
public class IterateString {

    private static void printChar(int aChar) { System.out.println((char)(aChar));
    }
    public static void main(String[] args) {
        final String str = "w00t";

        // note: chars() returns an IntStream which we can iterate over
        str.chars().forEach(ch -> System.out.println(ch));

        // Since we already provided a target for the method, the Java compiler decided to use the parameter of the
        // synthesized method as an argument to the referenced method, like so: Sys- tem.out.println(parameter);.
        str.chars().forEach(System.out::println);

        str.chars()
                .mapToObj(ch -> Character.valueOf((char)ch))
                .forEach(System.out::println);

        str.chars()
                .filter(ch -> Character.isDigit(ch))
                .forEach(ch -> printChar(ch));

        str.chars().filter(Character::isDigit).forEach(IterateString::printChar);

        str.chars()
                .filter(ch -> !Character.isDigit(ch))
                .forEach(ch -> printChar(ch));

    }
}
