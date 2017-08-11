package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;

public class RetrieveCommandTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
    private final BoxSpy box = new BoxSpy();
    private final RetrieveCommand rc = new RetrieveCommand();
    private final InputStream in = new ByteArrayInputStream("".getBytes());
    private final App app = new App(in, pw, box, "", null);

    public RetrieveCommandTest() throws IOException {}

    @Test
    public void CallsRetrieveOnBox() throws Exception {
        rc.execute("box", app);
        assertTrue(box.retrieveCalled);
    }

    @Test
    public void PrintsRetrievedContents() throws Exception {
        rc.execute("box", app);
        assertEquals("Bulbasaur\nHana\n", out.toString());
    }
    
    @Test
    public void RespondsToBox() throws Exception {
        assertTrue(rc.respondsTo("box"));
    }
}
