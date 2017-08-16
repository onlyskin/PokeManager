package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;

public class StoreCommandTest {

    private ByteArrayOutputStream out;
    private BoxSpy boxSpy = new BoxSpy();
    private UiSpy uiSpy = new UiSpy();

    public StoreCommandTest() throws IOException {}

    @Test
    public void CallsStoreOnBoxWithArgs() throws Exception {
       StoreCommand sc = makeStoreCommandWithInputStream("Charmander\nEmber\n21\n");
       sc.execute("store");
       assertTrue(boxSpy.storeCalled);
       assertTrue(uiSpy.getLevelCalled);
       assertTrue(uiSpy.getSpeciesCalled);
       assertTrue(uiSpy.getNicknameCalled);
       assertTrue(uiSpy.storeSuccessCalled);
       Pokemon p = boxSpy.stored.get(0);
       assertEquals("Charmander", p.getSpecies());
       assertEquals("Ember", p.getNickname());
       assertEquals(new Integer(21), p.getLevel());
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
