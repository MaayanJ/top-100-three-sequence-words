import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class MockUserInput {
    public void MockUserInput(String input) {
        Object orig = System.out;
        Object irig = System.in;

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os, false, StandardCharsets.UTF_8));

        ByteArrayInputStream is = new ByteArrayInputStream(input.getBytes());
        System.setIn(is);
    }
}
