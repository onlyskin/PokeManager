package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;

public class RetrieveCommandTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
    private final BoxSpy box = new BoxSpy(new ByteArrayInputStream("".getBytes()));
    private final RetrieveCommand rc = new RetrieveCommand(box, pw);

    public RetrieveCommandTest() throws IOException {}

    @Test
    public void CallsRetrieveOnBox() throws Exception {
        rc.execute();
        assertTrue(box.retrieveCalled);
    }

    @Test
    public void PrintsRetrievedContents() throws Exception {
        rc.execute();
        assertEquals("Bulbasaur\nHana\n", out.toString());
    }
}
