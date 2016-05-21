package fpij.collections;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jsimone on 12/8/15.
 */
public class Iteration {

    static final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {

        // imperitive style
        System.out.println("// imperitive style");
        for(int i = 0; i < friends.size(); i++) {
            System.out.println(friends.get(i));
        }

        // using an anonymous inner class
        System.out.println("// using an anonymous inner class");
        friends.forEach(new Consumer<String>() {
            public void accept(final String name) {
             System.out.println(name);
            }
          }
        );


        // using lambda
        System.out.println("// using lambda");
        friends.forEach((final String name) -> System.out.println(name));


        // using lambda
        System.out.println("// using lambda - leaving out the type");
        friends.forEach((name) -> System.out.println(name));


        // using lambda
        // we can leave off the parentheses around the parameter if the parameter’s type is inferred.
        System.out.println("// using lambda - no parens");
        friends.forEach(name -> System.out.println(name));


        System.out.println("// using a method reference");
        friends.forEach(System.out::println);
    }
}
