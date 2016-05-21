package fpij.compare;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jsimone on 12/11/15.
 */
public class ListFiles {
    public static void main(String[] args) throws Exception {

        // note list method returns a stream
        Files.list(Paths.get(".")).forEach(System.out::println);
    }
}
