package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;

// import ioc.Container;

public class AppTest {

    private final ByteArrayOutputStream out;
    private final PrintStream printStream;
    private InputStream input;
    private final FileBox box;
    private final File tempFile;
    private final HttpGetRequester getRequester;
    private App app;
    private Ui ui;
    private EnglishMessageProvider em;

    public AppTest() throws IOException {
        out = new ByteArrayOutputStream();
        printStream = new PrintStream(out);
        tempFile = File.createTempFile("temp-", "-testfile");
        tempFile.deleteOnExit();
        FileWriter fw = new FileWriter(tempFile.toString());
        fw.write("[{\"species\":\"Bulbasaur\"," +
                "\"nickname\":\"Hana\",\"level\":5,\"height\":7," +
                "\"weight\":69,\"dateCaught\":\"18/08/2015\"," +
                "\"locationCaught\":\"Cinnabar Island\",\"currentHp\":356}]");
        fw.close();
        box = new FileBox(tempFile.toString());
        getRequester = new HttpGetRequesterSpy();
        em = new EnglishMessageProvider();
    }

    @Test
    public void PrintsBox() throws Exception {
        RunAppWithUserInput("box\nexit\n");
        assertEquals(em.startupMessage() + "\n~Hana~ lv.5 Bulbasaur - 0.7m," +
                " 6.9kg - 356HP - caught on 18/08/2015 at Cinnabar Island\n\n",
                out.toString());
    }

    @Test
    public void StoresThenPrintsUpdatedBox() throws Exception {
        RunAppWithUserInput("store\nCharmander\nEmber\n6\n18/06/2015\n" +
                "Cinnabar Island\n356\nbox\nexit\n");
        assertEquals(em.startupMessage() + "\n" +
                em.getMessage("speciesInput") + "\n" +
                em.getMessage("nicknameInput") + "\n" +
                em.getMessage("levelInput") + "\n" +
                em.getMessage("dateInput") + "\n" +
                em.getMessage("locationInput") + "\n" +
                em.getMessage("hpInput") + "\n" +
                em.getMessage("storedSuccess") + "\n\n" +
                "~Hana~ lv.5 Bulbasaur - 0.7m, 6.9kg - 356HP - " +
                "caught on 18/08/2015 at Cinnabar Island\n" +
                "~Ember~ lv.6 Charmander - 0.7m, 6.9kg - 356HP - " +
                "caught on 18/06/2015 at Cinnabar Island\n\n", out.toString());
    }

    @Test
    public void SavesBoxToFile() throws Exception {
        RunAppWithUserInput("store\nCharmander\nEmber\n6\n18/06/2015\nCinnabar Island\n356\nsave\nexit\n");
        String fileContents = inputStreamToString(new FileInputStream(tempFile.toString()));
        assertEquals("[{\"species\":\"Bulbasaur\"," +
                "\"nickname\":\"Hana\",\"level\":5,\"height\":7," +
                "\"weight\":69,\"dateCaught\":\"18/08/2015\"," +
                "\"locationCaught\":\"Cinnabar Island\",\"currentHp\":356}," +
                "{\"species\":\"Charmander\",\"nickname\":\"Ember\",\"level\":6" +
                ",\"height\":30,\"weight\":30,\"dateCaught\":\"18/06/2015\"," +
                "\"locationCaught\":\"Cinnabar Island\",\"currentHp\":356}]", fileContents);
    }

    @Test
    public void GetsPokemonDataFromGetRequester() throws Exception {
        RunAppWithUserInput("search\nBulbasaur\nexit\n");
        assertEquals(em.startupMessage() + "\n" +
                em.getMessage("searchInput") + "\n" +
                em.getMessage("speciesField") + ": bulbasaur\n" +
                em.getMessage("heightField") + ": 0.7m\n" +
                em.getMessage("weightField") + ": 6.9kg\n", out.toString());
    }

    @Test
    public void PrintsErrorMessageOnInvalidCommand() throws Exception {
        RunAppWithUserInput("invalidcommand\nexit\n");
        assertEquals(em.startupMessage() + "\n" +
                em.getMessage("badCommand") + "\n", out.toString());
    }

    private void RunAppWithUserInput(String userInput) throws
            IllegalAccessException, InvocationTargetException, InstantiationException {
        input = new ByteArrayInputStream(userInput.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(userInput.getBytes())));
        ui = new Ui(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(userInput.getBytes()))),
                printStream, "en");
        app = new App(box, getRequester, ui);
        // Container container = new Container();
        // container.registerType(FileBox.class);
        // container.registerType(HttpGetRequester.class);
        // container.registerType(Ui.class);
        // container.registerInstance(reader);
        // container.registerInstance(printStream);
        // App app2 = container.construct(App.class, tempFile.toString(), "en");
        app.run();
    }

    public String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }

}
