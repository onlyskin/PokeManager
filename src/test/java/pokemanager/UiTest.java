package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;

public class UiTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(out);
    private final ByteArrayInputStream inputStream = new ByteArrayInputStream("".getBytes());
    private final BufferedReader reader = new BufferedReader(
            new InputStreamReader(inputStream));
    private final MessageProvider messageProvider = new MessageProviderStub();
    private Ui ui = new Ui(reader, printStream, messageProvider);

    @Test
    public void PrintsStartupMessage() throws Exception {
        ui.startupMessage();
        assertEquals("startup message\n", out.toString());
    }

    @Test
    public void PrintsBadCommandMessage() throws Exception {
        ui.badCommandMessage();
        assertEquals("bad command message\n\n", out.toString());
    }

    @Test
    public void PrintsEmptySpace() throws Exception {
        ui.emptySpace();
        assertEquals("\n", out.toString());
    }

    @Test
    public void DisplaysPokemonWithNullHeight() throws Exception {
        ui.displayPokemon(new Pokemon("Bulbasaur", "Hana", 13, null, null));
        assertEquals("Hana - lv.13 Bulbasaur\n", out.toString());
    }

    @Test
    public void DisplaysPokemon() throws Exception {
        ui.displayPokemon(new Pokemon("Bulbasaur", "Hana", 13, 7, 69));
        assertEquals("Hana - lv.13 Bulbasaur, 0.7m, 6.9kg\n", out.toString());
    }

    @Test
    public void GetsLevel() throws Exception {
        Ui ui = makeUiWithInputStreamString("3\n");
        assertEquals(new Integer(3), ui.getLevel());
        assertEquals("level request message\n", out.toString());
    }

    @Test
    public void AsksAgainIfCantParseNumber() throws Exception {
        Ui ui = makeUiWithInputStreamString("three\n3\n");
        assertEquals(new Integer(3), ui.getLevel());
        assertEquals("level request message\nlevel request message\n",
                out.toString());
    }

    @Test
    public void RejectsLevelOutsideRange() throws Exception {
        Ui ui = makeUiWithInputStreamString("100\n0\n3\n");
        assertEquals(new Integer(3), ui.getLevel());
    }

    @Test
    public void GetsSpecies() throws Exception {
        Ui ui = makeUiWithInputStreamString("Bulbasaur");
        assertEquals("Bulbasaur", ui.getSpecies());
        assertEquals("species request message\n", out.toString());
    }

    @Test
    public void GetsNickname() throws Exception {
        Ui ui = makeUiWithInputStreamString("Hana");
        assertEquals("Hana", ui.getNickname());
        assertEquals("nickname request message\n", out.toString());
    }

    @Test
    public void PrintsStoreSuccess() throws Exception {
        ui.storeSuccessMessage();
        assertEquals("store success message\n\n", out.toString());
    }

    @Test
    public void PrintsSaveSuccess() throws Exception {
        ui.saveSuccessMessage();
        assertEquals("save success message\n\n", out.toString());
    }

    @Test
    public void DisplaysSpecies() throws Exception {
        ui.displaySpecies(new Species("Bulbasaur", 5, 10));
        assertEquals("species fieldname: Bulbasaur\n" +
                     "height fieldname: 5\n" +
                     "weight fieldname: 10\n", out.toString());
    }

    @Test
    public void DisplaysNoneFoundMessage() throws Exception {
        ui.noneFoundMessage();
        assertEquals("none found message\n\n", out.toString());
    }

    @Test
    public void GetsSpeciesSearchInput() throws Exception {
        Ui ui = makeUiWithInputStreamString("Bulbasaur\n");
        String inputString = ui.getSpeciesSearchInput();
        assertEquals("Bulbasaur", inputString);
        assertEquals("search message\n", out.toString());
    }

    private Ui makeUiWithInputStreamString(String inputString) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new ByteArrayInputStream(inputString.getBytes())));
        return new Ui(reader, printStream, messageProvider);
    }
}
