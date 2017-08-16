package pokemanager;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

public class SpeciesCommandTest {
	
	public SpeciesCommandTest() throws IOException {}

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(out);
	private final BoxSpy box = new BoxSpy();
    private final SpeciesFinderSpy speciesFinder = new SpeciesFinderSpy();
    private final SpeciesCommand sc = new SpeciesCommand(speciesFinder, printStream);

    @Test
    public void CallsAndPrintsToStringOnReturnedSpecies() throws Exception {
        sc.execute("search Bulbasaur");
        assertEquals("Name: Bulbasaur\nHeight: 7\nWeight: 69\n", out.toString());
    }

    @Test
    public void PrintsNoneFoundOnNullReturn() throws Exception {
        sc.execute("search bad_input");
        assertEquals("no species found\n", out.toString());
    }
    @Test
    public void CallsFindDetailsOnApiSearcher() throws Exception {
        sc.execute("search Bulbasaur");
        assertTrue(speciesFinder.findDetailsCalled);
    }

	@Test
	public void PassesNameToApiSearcher() throws Exception {
		sc.execute("search Bulbasaur");
		assertEquals("Bulbasaur", speciesFinder.calledWith);
	}

	@Test
	public void RespondsToSearch() throws Exception {
		assertTrue(sc.respondsTo("search"));
	}
}
