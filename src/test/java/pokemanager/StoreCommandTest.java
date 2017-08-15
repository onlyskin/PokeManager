package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;

public class StoreCommandTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(out);
    private final BoxSpy boxSpy = new BoxSpy();
    private final StoreCommand sc = new StoreCommand();

    public StoreCommandTest() throws IOException {}

    @Test
    public void CallsStoreOnBoxWithArgs() throws Exception {
       App app = makeAppWithInputStream("Charmander\nEmber\n21\n");
       sc.execute("store", app);
       assertTrue(boxSpy.storeCalled);
       assertEquals("Species:\nNickname:\nLevel:\nStored!\n\n", out.toString());
       Pokemon p = boxSpy.stored.get(0);
       assertEquals("Charmander", p.getSpecies());
       assertEquals("Ember", p.getNickname());
       assertEquals(new Integer(21), p.getLevel());
    }

    @Test
    public void RejectsLevelAbove99() throws Exception {
        App app = makeAppWithInputStream("Charmander\nEmber\n103\n21\n");
        sc.execute("store", app);
        assertEquals("Species:\nNickname:\nLevel:\nLevel:\nStored!\n\n",
                out.toString());
        assertEquals(new Integer(21), boxSpy.stored.get(0).getLevel());
    }

    @Test
    public void RespondsToStore() throws Exception {
        assertTrue(sc.respondsTo("store Charmander Ember"));
    }

    private App makeAppWithInputStream(String inputString) {
        InputStream in = new ByteArrayInputStream(inputString.getBytes());
        return new App(in, printStream, boxSpy, null);
    }
}
