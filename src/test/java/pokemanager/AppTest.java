package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import java.util.stream.Collectors;

public class AppTest {

    private final String startMessage = "Commands:\n'box' to see stored Pokemon" +
                "\n'store SPECIES NICKNAME' to store a Pokemon" +
                "\n'save' to save your stored Pokemon for next time" +
                "\n'search SPECIES' to search the Pokedex";

    private final ByteArrayOutputStream out;
    private final PrintStream pw;
    private InputStream input;
    private final FileBox box;
    private final File tempFile;
    private final HttpGetRequester getRequester;
    private App app;

    public AppTest() throws IOException {
        out = new ByteArrayOutputStream();
        pw = new PrintStream(out);
        input = new ByteArrayInputStream("".getBytes());
        box = new FileBox(new ByteArrayInputStream("Bulbasaur\nHana\n".getBytes()));
        tempFile = File.createTempFile("temp-", "-testfile");
        tempFile.deleteOnExit();
        getRequester = new HttpGetRequesterSpy();
        app = new App(input, pw, box, tempFile.toString(), getRequester);
    }

    @Test
    public void PrintsBox() throws Exception {
        RunAppWithUserInput("box\nexit\n");
        assertEquals(startMessage + "\nHana\n(Bulbasaur)\n\n", out.toString());
    }

    @Test
    public void PrintsBoxWithStored() throws Exception {
        RunAppWithUserInput("store Charmander Ember\nbox\nexit\n");
        assertEquals(startMessage + "\nStored!\n\nHana\n(Bulbasaur)\nEmber\n(Charmander)\n\n", out.toString());
    }

    @Test
    public void SavesBoxToFile() throws Exception {
        RunAppWithUserInput("store Charmander Ember\nsave\nexit\n");
        String fileContents = inputStreamToString(new FileInputStream(tempFile.toString())); 
        assertEquals("Bulbasaur\nHana\nCharmander\nEmber", fileContents);
    }

    @Test
    public void GetsPokemonDataFromGetRequester() throws Exception {
        RunAppWithUserInput("search Bulbasaur\nexit\n");
        assertEquals(startMessage + "\nSpecies: bulbasaur\nHeight: 7\nWeight: 69\n\n", out.toString());
    }

    @Test
    public void PrintsErrorMessageOnInvalidCommand() throws Exception {
        RunAppWithUserInput("invalidcommand\nexit\n");
        assertEquals(startMessage + "\nPlease enter a valid command.\n\n", out.toString());
    }

	@Test
	public void GetsStoragePath() {
		assertEquals(tempFile.toString(), app.getStoragePath());
	}

	@Test
	public void GetsBox() {
		assertEquals(box, app.getBox());
	}

    private void RunAppWithUserInput(String userInput) {
        input = new ByteArrayInputStream(userInput.getBytes());
        app = new App(input, pw, box, tempFile.toString(), getRequester);
        app.run();
    }

    public String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }

}
