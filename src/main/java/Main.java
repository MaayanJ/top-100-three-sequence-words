import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main (String[] args) {
//        System.out.println(args[0]);
//        String name = "";
//        processWholeFile(name);
        InputProcessor inputProcessor = new InputProcessor();
        inputProcessor.processInputType(args);

    }

//    public static void processWholeFile(String fileName) {
////        Path pathToFile = Paths.get("mobydick.txt");
////        System.out.println("printtttt" + pathToFile.toAbsolutePath());
//        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/mobydick.txt"))) {
//            System.out.println(br);
//
//
//        } catch (IOException e) {
//            System.err.println("File not found.");
//            e.printStackTrace();
//        }
//    }
}
