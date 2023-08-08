import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputProcessor {


    /**
     * If file paths are given through arguments, the method will process those files.
     * If arguments list is empty, a user will be triggered to input
     * file path/s through STDIN
     * The paths are stored in a set so that duplicate path entries don't cause the same file
     * to be processed more than once
     * @param filePaths - file path input from arguments
     */
    public void processInputType(String[] filePaths) {
        Set<String> paths = new HashSet<>();
        if (filePaths == null || filePaths.length == 0) {
            //using set so that the same file is not being read more than once, causing performance issues
            paths =  processStdin();
        } else if (filePaths.length > 0) {
            paths = new HashSet<>(Arrays.asList(filePaths));

        }
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.processFiles(paths);
    }

    /**
     * This helper method takes in the stdin input and detects whether it contains
     * more than one path
     * and validates that the file can be found
     */
    Set<String> processStdin(Scanner scanner) {
        System.out.println("Please enter the file path you would like processed: ");
        scanner = new Scanner(System.in);
        String input = scanner.next();
        //prevent memory leaks
        scanner.close();
        Set<String> paths = new HashSet<>(Arrays.asList(input.split(",")));
        return paths;
    }

    public Set<String> processStdin() {
        return processStdin(new Scanner(System.in));
    }

}

