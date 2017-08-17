package pokemanager;

import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import java.util.stream.Collectors;

public class AppTest {

    private final ByteArrayOutputStream out;
    private final PrintStream printStream;
    private InputStream input;
    private final FileBox box;
    private final File tempFile;
    private final HttpGetRequester getRequester;
    private App app;
    private Ui ui;

    public AppTest() throws IOException {
        out = new ByteArrayOutputStream();
        printStream = new PrintStream(out);
        tempFile = File.createTempFile("temp-", "-testfile");
        tempFile.deleteOnExit();
        FileWriter fw = new FileWriter(tempFile.toString());
        fw.write("[{\"species\":\"Bulbasaur\",\"nickname\":\"Hana\",\"level\":5}]");
        fw.close();
        box = new FileBox(tempFile.toString());
        getRequester = new HttpGetRequesterSpy();
    }

    @Test
    public void PrintsBox() throws Exception {
        RunAppWithUserInput("box\nexit\n");
        assertEquals("startup message\nHana - lv.5 Bulbasaur\n\n", out.toString());
    }

    @Test
    public void StoresThenPrintsUpdatedBox() throws Exception {
        RunAppWithUserInput("store\nCharmander\nEmber\n6\nbox\nexit\n");
        assertEquals("startup message\nspecies request message\nnickname request message\n" +
                "level request message\nstore success message\n\n" +
            "Hana - lv.5 Bulbasaur\nEmber - lv.6 Charmander\n\n", out.toString());
    }

    @Test
    public void SavesBoxToFile() throws Exception {
        RunAppWithUserInput("store\nCharmander\nEmber\n6\nsave\nexit\n");
        String fileContents = inputStreamToString(new FileInputStream(tempFile.toString()));
        assertEquals("[{\"level\":5,\"species\":\"Bulbasaur\",\"nickname\":\"Hana\"}," +
                "{\"level\":6,\"species\":\"Charmander\",\"nickname\":\"Ember\"}]",
                fileContents);
    }

    @Test
    public void GetsPokemonDataFromGetRequester() throws Exception {
        RunAppWithUserInput("search\nBulbasaur\nexit\n");
        assertEquals("startup message\n" +
                "search message\n" +
                "species fieldname: bulbasaur\n" +
                "height fieldname: 7\n" +
                "weight fieldname: 69\n",
                out.toString());
    }

    @Test
    public void PrintsErrorMessageOnInvalidCommand() throws Exception {
        RunAppWithUserInput("invalidcommand\nexit\n");
        assertEquals("startup message\nbad command message\n\n",
                out.toString());
    }

    private void RunAppWithUserInput(String userInput) {
        input = new ByteArrayInputStream(userInput.getBytes());
        ui = new Ui(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(userInput.getBytes()))),
                printStream, new MessageProviderStub());
        app = new App(box, getRequester, ui);
        app.run();
    }

    public String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }

}
