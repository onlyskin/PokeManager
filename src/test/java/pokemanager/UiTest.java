package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class UiTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(out);
    private final ByteArrayInputStream inputStream = new ByteArrayInputStream("".getBytes());
    private final BufferedReader reader = new BufferedReader(
            new InputStreamReader(inputStream));
    private final MessageProvider messageProvider = new MessageProviderStub();
    private Ui ui = new Ui(reader, printStream, messageProvider);

    @Test
    public void PrintsLine() throws Exception {
        ui.display("Test");
        assertEquals("Test\n", out.toString());
    }

    @Test
    public void PrintsStartupMessage() throws Exception {
        ui.startupMessage();
        String expected = "startup message\n";
        assertEquals(expected, out.toString());
    }
}
