package pokemanager;

import org.junit.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AppTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
    private final BoxSpy box = new BoxSpy(new ByteArrayInputStream("".getBytes()));
    private final File tempFile = File.createTempFile("temp-", "-testfile");

    public AppTest() throws IOException {
        tempFile.deleteOnExit();
    }

    @Test
    public void TestItCallsRetrieveOnBoxWhenUserInputsBox() throws Exception {
        acceptInput("box");
        assertTrue(box.retrieveCalled);
    }

    @Test
    public void TestItPrintsRetrievedContentsWhenUserInputsBox() throws Exception {
        acceptInput("box");
        assertEquals("Bulbasaur\nHana\n", out.toString());
    }

    @Test
    public void TestItCallsStoreWithPokemonNameOnBoxWhenUserInputsStore() throws Exception {
        acceptInput("store Charmander");
        assertEquals(box.stored, "Charmander");
    }

    @Test
    public void TestItPrintsStoredAfterPokemonIsStored() throws Exception {
        acceptInput("store Charmander Ember");
        assertEquals(out.toString(), "Stored!\n\n");
    }

    @Test
    public void TestItPrintsSavedAfterPokemonAreSaved() throws Exception {
        acceptInput("save");
        assertEquals(out.toString(), "Saved!\n\n");
    }

    @Test
    public void TestItCallsGetDataStringOnBoxWhenUserInputsSave() throws Exception {
        acceptInput("save");
        assertTrue(box.getDataStringCalled);
    }

    @Test
    public void TestCallsGetDataStringOnBoxWhenUserInputsSaveAndSavesToTempFile() throws IOException {
        acceptInput("save");
        String tempFileContents = inputStreamToString(new FileInputStream(tempFile.toString()));
        assertEquals(tempFileContents, "Bulbasaur\nHana");
    }

    @Test
    public void TestPrintsErrorMessageWhenUserInputsInvalidCommand() throws Exception {
        acceptInput("invalidcommand");
        assertEquals("Please enter a valid command.\n\n", out.toString());
    }

    private void acceptInput(String inputString) throws IOException {
        InputStream input = new ByteArrayInputStream(inputString.getBytes());
        App app = new App(input, pw, box, tempFile.toString());
        app.acceptInput();
    }

    public String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }
}
