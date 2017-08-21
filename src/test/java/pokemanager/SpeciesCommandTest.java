package pokemanager;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import java.io.*;

public class SpeciesCommandTest {
	
	public SpeciesCommandTest() throws IOException {}

    private final SpeciesFinderSpy speciesFinderSpy = new SpeciesFinderSpy();

	@Test
    public void CallsDisplaySpeciesOnUiWhenSpeciesFound() throws Exception {
        UiStubSpy uiStubSpy = new UiStubSpy(false);
        SpeciesCommand sc = new SpeciesCommand(speciesFinderSpy, uiStubSpy);
        sc.execute("search");
        assertTrue(uiStubSpy.displaySpeciesCalled);
    }

	@Test
    public void CallsDisplayNoneFoundWhenNullReturned() throws Exception {
        UiStubSpy uiStubSpy = new UiStubSpy(true);
        SpeciesCommand sc = new SpeciesCommand(speciesFinderSpy, uiStubSpy);
        sc.execute("search");
        assertTrue(uiStubSpy.noneFoundCalled);
    }

    @Test
    public void CallsGetSpeciesSearchInputOnUiAndPassesResultToSpeciesFinder() throws Exception {
        UiSpy uiSpy = new UiSpy();
        SpeciesCommand sc = new SpeciesCommand(speciesFinderSpy, uiSpy);
        sc.execute("search");
        assertTrue(uiSpy.getSpeciesSearchInputCalled);
		assertEquals("Bulbasaur", speciesFinderSpy.calledWith);
    }

	@Test
    public void CallsFindDetailsOnSpeciesFinder() throws Exception {
        Ui ui = makeUiWithInputStreamString("Bulbasaur");
        SpeciesCommand sc = new SpeciesCommand(speciesFinderSpy, ui);
        sc.execute("search");
        assertTrue(speciesFinderSpy.findDetailsCalled);
    }

	@Test
	public void RespondsToSearch() throws Exception {
        Ui ui = makeUiWithInputStreamString("Bulbasaur");
        SpeciesCommand sc = new SpeciesCommand(speciesFinderSpy, ui);
		assertTrue(sc.respondsTo("search"));
	}

    private Ui makeUiWithInputStreamString(String inputString) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new ByteArrayInputStream(inputString.getBytes())));
        return new Ui(reader, printStream, "en");
    }
}
