package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;

public class StoreCommandTest {

    private ByteArrayOutputStream out;
    private BoxSpy boxSpy = new BoxSpy();

    public StoreCommandTest() throws IOException {}

    @Test
    public void CallsStoreOnBoxWithArgs() throws Exception {
       StoreCommand sc = makeStoreCommandWithInputStream("Charmander\nEmber\n21\n");
       sc.execute("store");
       assertTrue(boxSpy.storeCalled);
       assertEquals("Species:\nNickname:\nLevel:\nStored!\n\n", out.toString());
       Pokemon p = boxSpy.stored.get(0);
       assertEquals("Charmander", p.getSpecies());
       assertEquals("Ember", p.getNickname());
       assertEquals(new Integer(21), p.getLevel());
    }

    @Test
    public void RejectsLevelAbove99() throws Exception {
        StoreCommand sc = makeStoreCommandWithInputStream("Charmander\nEmber\n103\n21\n");
        sc.execute("store");
        assertEquals("Species:\nNickname:\nLevel:\nLevel:\nStored!\n\n",
                out.toString());
        assertEquals(new Integer(21), boxSpy.stored.get(0).getLevel());
    }

    @Test
    public void RespondsToStore() throws Exception {
        StoreCommand sc = new StoreCommand(null, null, null);
        assertTrue(sc.respondsTo("store"));
    }

    private StoreCommand makeStoreCommandWithInputStream(String inputString) {
        InputStream in = new ByteArrayInputStream(inputString.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        return new StoreCommand(boxSpy, reader, printStream);
    }
}
