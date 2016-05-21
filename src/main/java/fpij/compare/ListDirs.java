package fpij.compare;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jsimone on 12/11/15.
 */
public class ListDirs {
    public static void main(String[] args) throws Exception {

        // all sub directories
        // note list method returns a stream
        Files.list(Paths.get("."))
                .filter(Files::isDirectory)
                .forEach(System.out::println);
    }
}
