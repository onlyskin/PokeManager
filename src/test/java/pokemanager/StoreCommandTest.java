package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;

public class StoreCommandTest {

    private ByteArrayOutputStream out;
    private BoxSpy boxSpy = new BoxSpy();
    private SpeciesFinderSpy speciesFinderSpy = new SpeciesFinderSpy();

    public StoreCommandTest() throws IOException {}

    @Test
    public void CallsMethodsOnUI() throws Exception {
       UiSpy uiSpy = new UiSpy();
       StoreCommand sc = new StoreCommand(boxSpy, new SpeciesFinderStub(), uiSpy);
       sc.execute("store");
       assertTrue(uiSpy.getLevelCalled);
       assertTrue(uiSpy.getSpeciesCalled);
       assertTrue(uiSpy.getNicknameCalled);
       assertTrue(uiSpy.getDateCaughtCalled);
       assertTrue(uiSpy.getLocationCaughtCalled);
       assertTrue(uiSpy.getCurrentHpCalled);
       assertTrue(uiSpy.storeSuccessCalled);
    }

    @Test
    public void CallsFindDetailsOnSpeciesFinderWithSpecies() throws Exception {
       Ui ui = makeUiWithInputStreamString("Charmander\nEmber\n21\n" +
               "18/08/2015\nCinnabar Island\n356");
       StoreCommand sc = new StoreCommand(boxSpy, speciesFinderSpy, ui);
       sc.execute("store");
       assertTrue(speciesFinderSpy.findDetailsCalled); 
       assertEquals("Charmander", speciesFinderSpy.calledWith);
    }

    @Test
    public void CallsStoreOnBoxWithArgs() throws Exception {
       Ui ui = makeUiWithInputStreamString("Charmander\nEmber\n21\n" +
               "18/08/2015\nCinnabar Island\n356");
       StoreCommand sc = new StoreCommand(boxSpy, speciesFinderSpy, ui);
       sc.execute("store");
       assertTrue(boxSpy.storeCalled);
       Pokemon p = boxSpy.stored.get(0);
       assertEquals("Charmander", p.getSpecies());
       assertEquals("Ember", p.getNickname());
       assertEquals(new Integer(21), p.getLevel());
       assertEquals("18/08/2015", p.getDateCaught());
       assertEquals("Cinnabar Island", p.getLocationCaught());
       assertEquals(new Integer(356), p.getCurrentHp());
    }

    @Test
    public void RespondsToStore() throws Exception {
        Ui ui = new Ui(null, null, new MessageProviderStub());
        StoreCommand sc = new StoreCommand(null, null, ui);
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
