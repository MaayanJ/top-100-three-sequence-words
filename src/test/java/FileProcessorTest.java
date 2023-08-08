import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.util.Scanner;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileProcessorTest {
    //since we are allowing for two different input types for file path,
    // we want to make sure both work as excpected


//    @Test
//    public void testUserInputThroughArgs() {
//        InputProcessor processor = new InputProcessor();
//        String input = "src/main/resources/mobydick.txt";
//        Set<String> paths = processor.processStdin(new Scanner(input));
//        assertEquals("10", paths);
//
////        assertThat(output.toString(), contains("Wrong number, try again.")););
//    }

    //test processFiles method


    //test readFiles:
    //This is a blackbox test - we only care about the input (file path) and the output (the content of the file)
    //since we want tests to be as flexible as possible.
    //By only testing behivoiur under different conditions, our tests are not tightly coupled with the intenrnal
    //implementation of methods, and we are better able to modify our code without breaking tests
    @Test
    public void testFileIsReadAndContentFromFileIsReturned() throws IOException{
        String filePath = "src/test/resources/Test.txt";
        FileProcessor fileProcessor = new FileProcessor();
        String content = fileProcessor.readFiles(filePath);
        assertEquals("This is a test file ", content);
    }

    @Test
    public void testFilePathNotFoundThrowsIOException() throws IOException {
        String filePath = "src/main/resources/mobydck.txt";
        FileProcessor fileProcessor = new FileProcessor();
        assertThrows(IOException.class, () -> {
            fileProcessor.readFiles(filePath);
        });
    }

    @Test
    public void testFileWithNoTextFoundReturnsEmptyString() throws IOException{
        String filePath = "src/test/resources/Empty.txt";
        FileProcessor fileProcessor = new FileProcessor();
        String content = fileProcessor.readFiles(filePath);
        assertEquals("", content);
    }

    @Test
    public void testValidateContentReturnsContentWithoutPunctuation() {
        FileProcessor fileProcessor = new FileProcessor();
        String content = fileProcessor.validateContent("The Project Gutenberg EBook of Moby Dick; or The Whale, by Herman Melville " +
                "This eBook is for the use of anyone anywhere at no cost and with almost no restrictions whatsoever.  You may copy it, give it away or\n" +
                "re-use it under the terms of the Project Gutenberg' License included\n" +
                "with this eBook or onl'ine at www.gutenberg.org\n" +
                "\n" +
                "\n" +
                "Title: Moby Dick; or The Whale");
        assertEquals("the project gutenberg ebook of moby dick or the whale by herman melville this ebook is for the use of anyone anywhere at no cost and with " +
                "almost no restrictions whatsoever you may copy it give it away or " +
                "re use it under the terms of the project gutenberg license included " +
                "with this ebook or online at www gutenberg org " +
                "title moby dick or the whale", content);
    }

    @Test
    public void testValidateContentReplacesMultipleWhiteSpacesWithOnlyOneSpace() {
        FileProcessor fileProcessor = new FileProcessor();
        String content = fileProcessor.validateContent("The          Project   Gutenberg ");
        assertEquals("the project gutenberg ", content);
    }

    @Test
    public void testValidateContentReplacesAllPunctuationExceptForApostropheWithOneWhiteSpace() {
        FileProcessor fileProcessor = new FileProcessor();
        String content = fileProcessor.validateContent("The\" %&$#%*##$^*(@Project-Gutenberg ");
        assertEquals("the project gutenberg ", content);
    }

    @Test
    public void testValidateContentReplacesApostropheWithAnEMptyString() {
        FileProcessor fileProcessor = new FileProcessor();
        String content = fileProcessor.validateContent("The Project's Gutenberg's ");
        assertEquals("the projects gutenbergs ", content);
    }

    @Test
    public void testValidateContentReplacesAllUpperCaseLettersWithLowerCase() {
        FileProcessor fileProcessor = new FileProcessor();
        String content = fileProcessor.validateContent("HELLO THIS IS ME YOU ARE LOOKING FOR ");
        assertEquals("hello this is me you are looking for ", content);
    }

    @Test void testValidateCOntentReplacesUnicodeCharactersWithWhiteSpace() {
        FileProcessor fileProcessor = new FileProcessor();
        String content = fileProcessor.validateContent("土竹竹金土大心心山山土口女女火火十火金山 �à ካለኝ እንደ função, Ãugent  ⠊⠞⠲ ⡁⠝⠙ Καλημέρα κόσμε, コンニチハ");
        assertEquals(" fun o ugent ", content);
    }


//    @Test void readFile() throws IOException {
//        FileProcessor fileProcessor = new FileProcessor();
//        String content = fileProcessor.readFiles("src/test/resources/UnicodeText.txt");
//        String finalC = fileProcessor.validateContent(content);
//        assertEquals(" ", finalC);
//    }

}




//src/main/resources/mobydick.txt