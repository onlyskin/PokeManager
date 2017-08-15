package pokemanager;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

public class SpeciesCommandTest {
	
	public SpeciesCommandTest() throws IOException {}

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(out);
	private final BoxSpy box = new BoxSpy();
    private final InputStream in = new ByteArrayInputStream("".getBytes());
    private final App app = new App(in, printStream, box, null);
    private final SpeciesFinderSpy speciesFinder = new SpeciesFinderSpy();
    private final SpeciesCommand sc = new SpeciesCommand(speciesFinder);

    @Test
    public void CallsAndPrintsToStringOnReturnedSpecies() throws Exception {
        sc.execute("search Bulbasaur", app);
        assertEquals("Name: Bulbasaur\nHeight: 7\nWeight: 69\n", out.toString());
    }

    @Test
    public void PrintsNoneFoundOnNullReturn() throws Exception {
        sc.execute("search bad_input", app);
        assertEquals("no species found\n", out.toString());
    }
    @Test
    public void CallsFindDetailsOnApiSearcher() throws Exception {
        sc.execute("search Bulbasaur", app);
        assertTrue(speciesFinder.findDetailsCalled);
    }

	@Test
	public void PassesNameToApiSearcher() throws Exception {
		sc.execute("search Bulbasaur", app);
		assertEquals("Bulbasaur", speciesFinder.calledWith);
	}

	@Test
	public void RespondsToSearch() throws Exception {
		assertTrue(sc.respondsTo("search"));
	}
}
