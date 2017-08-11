package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ExitCommandTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
    private final BoxSpy box = new BoxSpy();
    private final ExitCommand ec = new ExitCommand();
    private final InputStream in = new ByteArrayInputStream("".getBytes());
    private final AppSpy app = new AppSpy(in, pw, box, "", null);

    public ExitCommandTest() throws IOException {}

    @Test
    public void CallsExitOnApp() throws Exception {
        ec.execute("exit", app);
        assertTrue(app.exitCalled);
    }
}
