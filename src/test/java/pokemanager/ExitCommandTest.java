package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ExitCommandTest {
    private final AppSpy app = new AppSpy();
    private final ExitCommand ec = new ExitCommand(app);

    public ExitCommandTest() throws IOException {}

    @Test
    public void CallsExitOnApp() throws Exception {
        ec.execute("exit");
        assertTrue(app.exitCalled);
    }
}
