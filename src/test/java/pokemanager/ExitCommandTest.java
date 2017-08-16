package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ExitCommandTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(out);
    private final InputStream in = new ByteArrayInputStream("".getBytes());
    private final AppSpy app = new AppSpy(in, printStream, null, null);
    private final ExitCommand ec = new ExitCommand(app);

    public ExitCommandTest() throws IOException {}

    @Test
    public void CallsExitOnApp() throws Exception {
        ec.execute("exit");
        assertTrue(app.exitCalled);
    }
}
