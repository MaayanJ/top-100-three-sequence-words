import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputProcessor {
    public InputProcessor(String[] args) {
        processInputType(args);
    }

    /**
     * If arguments list is empty, a user will be triggered to input
     * a file path through STDIN
     *
     * @param filePath - file path input from arguments
     */
    public void processInputType(String[] filePath) {
        Set<String> paths = new HashSet<>();
        if (filePath == null || filePath.length == 0) {
            System.out.println("Please enter the file path you would like processed: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            //using set so that the same file is not being read more than once, causing performance issues
            paths =  processStdin(input);
            //prevent memory leaks
            scanner.close();
        } else if (filePath.length > 0) {
            paths = new HashSet<>(Arrays.asList(filePath));

        }
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.processFiles(paths);
        //iterate through args list
        //validate paths
        //fail soft - if one or more of the files are invalid path, inform user which files you are processing
        //if the file is not found - fail soft and tell the use which files are found and which not

    }

    /**
     * This helper method takes in the stdin input and detects whether it containts
     * more than one path
     * and validates that the file can be found
     * @param input
     */
    private Set<String> processStdin(String input) {
        Set<String> paths = new HashSet<>(Arrays.asList(input.split(",")));
        return paths;
    }

}

