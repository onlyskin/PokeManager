package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SaveCommandTest {
	
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(out);
	private final BoxSpy boxSpy = new BoxSpy();
	private final SaveCommand sc = new SaveCommand(boxSpy, printStream);

	public SaveCommandTest() throws IOException {}

	@Test
	public void CallsSaveOnBox() throws Exception {
		sc.execute("save");
		assertTrue(boxSpy.saveCalled);
	}

	@Test
	public void PrintsSaved() throws Exception {
		sc.execute("save");
		assertEquals("Saved!\n\n", out.toString());
	}	

	@Test
	public void RespondsToSave() throws Exception {
		assertTrue(sc.respondsTo("save"));
	}
}	
