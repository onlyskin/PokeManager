package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ExitCommandTest {
    private final AppSpy app = new AppSpy();
    private final Ui ui = new Ui(null, null, new MessageProviderStub());
    private final ExitCommand ec = new ExitCommand(app, ui);

    public ExitCommandTest() throws IOException {}

    @Test
    public void CallsExitOnApp() throws Exception {
        ec.execute("exit");
        assertTrue(app.exitCalled);
    }

    @Test
    public void RespondsToExit() throws Exception {
        assertTrue(ec.respondsTo("exit"));
    }
}
