import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AppTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);

    @Test
    public void TestItOutputsListOfPokemon() {
        InputStream input = new ByteArrayInputStream("all".getBytes());
        App app = new App(input, pw);
        app.acceptInput();
        assertEquals("Gyarados\n", out.toString());
    }
}
