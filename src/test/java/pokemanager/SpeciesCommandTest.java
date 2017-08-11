package pokemanager;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

public class SpeciesCommandTest {
	
	public SpeciesCommandTest() throws IOException {}

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
	private final BoxSpy box = new BoxSpy();
    private final InputStream in = new ByteArrayInputStream("".getBytes());
    private final File tempFile = File.createTempFile("temp-", "-testfile");
    private final App app = new App(in, pw, box, tempFile.toString(), new HttpGetRequesterSpy());
    private final SpeciesFinderSpy apiSearcher = new SpeciesFinderSpy();
    private final SpeciesCommand sc = new SpeciesCommand(apiSearcher);

    @Test
    public void CallsFindDetailsOnApiSearcher() throws Exception {
        sc.execute("search Bulbasaur", app);
        assertTrue(apiSearcher.findDetailsCalled);
    }

	@Test
	public void PassesNameToApiSearcher() throws Exception {
		sc.execute("search Bulbasaur", app);
		assertEquals("Bulbasaur", apiSearcher.calledWith);
	}

	@Test
	public void RespondsToSearch() throws Exception {
		assertTrue(sc.respondsTo("search"));
	}

	@Test
	public void CallsAndPrintsToStringOnReturnedSpecies() throws Exception {
		sc.execute("search Bulbasaur", app);
		assertEquals("Name: Bulbasaur\nHeight: 7\nWeight: 69\n", out.toString());
	}

}
