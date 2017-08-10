package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SaveCommandTest {
	
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
	private final BoxSpy box = new BoxSpy(new ByteArrayInputStream("".getBytes()));
	private final SaveCommand sc = new SaveCommand();
    private final InputStream in = new ByteArrayInputStream("".getBytes());
    private final File tempFile = File.createTempFile("temp-", "-testfile");
    private final App app = new App(in, pw, box, tempFile.toString(), null);

	public SaveCommandTest() throws IOException {}

	@Test
	public void SavesToTempFile() throws Exception {
		sc.execute("save", app);
		String fileContents = inputStreamToString(new FileInputStream(tempFile.toString()));
		assertEquals("Bulbasaur\nHana", fileContents);
	}	

	@Test
	public void CallsGetDataStringOnBox() throws Exception {
		sc.execute("save", app);
		assertTrue(box.getDataStringCalled);
	}

	@Test
	public void PrintsSaved() throws Exception {
		sc.execute("save", app);
		assertEquals(out.toString(), "Saved!\n\n");
	}	

	@Test
	public void RespondsToSave() throws Exception {
		assertTrue(sc.respondsTo("save"));
	}

    public String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }
}	
