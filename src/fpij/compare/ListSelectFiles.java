package fpij.compare;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jsimone on 12/11/15.
 */
public class ListSelectFiles {

    public static void main(String[] args) throws Exception {

        Files.newDirectoryStream(
                Paths.get("./src/fpij/compare"), path -> path.toString().endsWith(".java"))
                .forEach(System.out::println);
    }

}
