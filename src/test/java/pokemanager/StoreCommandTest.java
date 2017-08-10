package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class StoreCommandTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
    private final BoxSpy box = new BoxSpy(new ByteArrayInputStream("".getBytes()));
    private final StoreCommand sc = new StoreCommand();
    private final InputStream in = new ByteArrayInputStream("".getBytes());
    private final App app = new App(in, pw, box, "", null);

    public StoreCommandTest() throws IOException {}

    @Test
    public void CallsStoreOnBox() throws Exception {
       sc.execute("store Charmander Ember", app);
       assertTrue(box.storeCalled);
    }

    @Test
    public void PrintsStored() throws Exception {
        sc.execute("store Charmander Ember", app);
        assertEquals("Stored!\n\n", out.toString());
    }
    
    @Test
    public void RespondsToStore() throws Exception {
        assertTrue(sc.respondsTo("store Charmander Ember"));
    }
}
