package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;

public class StoreCommandTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
    private final BoxSpy box = new BoxSpy(new ByteArrayInputStream("".getBytes()));
    private final StoreCommand sc = new StoreCommand(box, pw);

    public StoreCommandTest() throws IOException {}

    @Test
    public void CallsStoreOnBox() throws Exception {
       sc.execute("store Charmander Ember");
       assertTrue(box.storeCalled);
    }

    @Test
    public void PrintsStored() throws Exception {
        sc.execute("store Charmander Ember");
        assertEquals("Stored!\n\n", out.toString());
    }
}
