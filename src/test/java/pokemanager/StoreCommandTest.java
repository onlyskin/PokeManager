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
    public void CallsMethodsOnUI() throws Exception {
       StoreCommand sc = new StoreCommand(boxSpy, uiSpy);
       sc.execute("store");
       assertTrue(uiSpy.getLevelCalled);
       assertTrue(uiSpy.getSpeciesCalled);
       assertTrue(uiSpy.getNicknameCalled);
       assertTrue(uiSpy.storeSuccessCalled);
    }

    @Test
    public void CallsStoreOnBoxWithArgs() throws Exception {
       Ui ui = makeUiWithInputStreamString("Charmander\nEmber\n21\n");
       StoreCommand sc = new StoreCommand(boxSpy, ui);
       sc.execute("store");
       assertTrue(boxSpy.storeCalled);
       Pokemon p = boxSpy.stored.get(0);
       assertEquals("Charmander", p.getSpecies());
       assertEquals("Ember", p.getNickname());
       assertEquals(new Integer(21), p.getLevel());
    }

    @Test
    public void RespondsToStore() throws Exception {
        StoreCommand sc = new StoreCommand(null, null);
        assertTrue(sc.respondsTo("store"));
    }

    private Ui makeUiWithInputStreamString(String inputString) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new ByteArrayInputStream(inputString.getBytes())));
        return new Ui(reader, printStream, new MessageProviderStub());
    }
}
