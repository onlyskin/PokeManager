package pokemanager;

import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AppTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
    private final BoxSpy box = new BoxSpy(new ByteArrayInputStream("".getBytes()));

    public AppTest() throws IOException {
    }

    @Test
    public void TestItCallsRetrieveOnBoxWhenUserInputsBox() throws Exception {
        InputStream input = new ByteArrayInputStream("box".getBytes());
        App app = new App(input, pw, box, "");
        app.acceptInput();
        assertTrue(box.retrieveCalled);
    }

    @Test
    public void TestItPrintsRetrievedContentsWhenUserInputsBox() throws Exception {
        InputStream input = new ByteArrayInputStream("box".getBytes());
        App app = new App(input, pw, box, "");
        app.acceptInput();
        assertEquals("Bulbasaur\nHana\n", out.toString());
    }

    @Test
    public void TestItCallsStoreWithPokemonNameOnBoxWhenUserInputsStore() throws Exception {
        InputStream input = new ByteArrayInputStream("store Charmander".getBytes());
        App app = new App(input, pw, box, "");
        app.acceptInput();
        assertEquals(box.stored, "Charmander");
    }

    @Test
    public void TestCallsGetDataStringOnBoxWhenUserInputsSave() throws IOException {
        InputStream input = new ByteArrayInputStream("save".getBytes());
        File tempFile = File.createTempFile("temp-", "-testfile");
        tempFile.deleteOnExit();

        App app = new App(input, pw, box, tempFile.toString());
        app.acceptInput();

        String tempFileContents = inputStreamToString(new FileInputStream(tempFile.toString()));
        assertTrue(box.getDataStringCalled);
        assertEquals(tempFileContents, "Bulbasaur\nHana");
    }

    public String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }
}
