package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class StoreCommandTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(out);
    private final BoxSpy boxSpy = new BoxSpy();
    private final StoreCommand sc = new StoreCommand();
    private final InputStream in = new ByteArrayInputStream("Charmander\nEmber\n21\n".getBytes());
    private final App app = new App(in, printStream, boxSpy, null);

    public StoreCommandTest() throws IOException {}

    @Test
    public void CallsStoreOnBoxWithArgs() throws Exception {
       sc.execute("store", app);
       assertTrue(boxSpy.storeCalled);
       assertEquals("Species:\nNickname:\nLevel:\nStored!\n\n", out.toString());
       Pokemon p = boxSpy.stored.get(0);
       assertEquals("Charmander", p.getSpecies());
       assertEquals("Ember", p.getNickname());
       assertEquals(new Integer(21), p.getLevel());
    }

    @Test
    public void RespondsToStore() throws Exception {
        assertTrue(sc.respondsTo("store Charmander Ember"));
    }
}
