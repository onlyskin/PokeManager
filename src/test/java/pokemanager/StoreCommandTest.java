package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class StoreCommandTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
    private final BoxSpy box = new BoxSpy();
    private final StoreCommand sc = new StoreCommand();
    private final InputStream in = new ByteArrayInputStream("".getBytes());
    private final App app = new App(in, pw, box, null);

    public StoreCommandTest() throws IOException {}

    @Test
    public void CallsStoreOnBoxWithArgs() throws Exception {
       sc.execute("store Charmander Ember 21", app);
       assertTrue(box.storeCalled);
       assertEquals("Charmander", box.speciesArg);
       assertEquals("Ember", box.nicknameArg);
       assertEquals(new Integer(21), box.levelArg);
    }

    @Test
    public void PrintsStored() throws Exception {
        sc.execute("store Charmander Ember 21", app);
        assertEquals("Stored!\n\n", out.toString());
    }
    
    @Test
    public void RespondsToStore() throws Exception {
        assertTrue(sc.respondsTo("store Charmander Ember 21"));
    }
}
