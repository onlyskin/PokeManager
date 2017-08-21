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
    private EnglishMessageProvider em = new EnglishMessageProvider();
    private Ui ui = new Ui(reader, printStream, "en");

    @Test
    public void PrintsStartupMessage() throws Exception {
        ui.startupMessage();
        assertEquals(em.startupMessage() + "\n", out.toString());
    }

    @Test
    public void PrintsEnStartupMessage() throws Exception {
        ui = new Ui(reader, printStream, "en");
        ui.badCommandMessage();
        assertEquals("Please enter a valid command.\n\n", out.toString());
    }

    @Test
    public void PrintsItStartupMessage() throws Exception {
        ui = new Ui(reader, printStream, "it");
        ui.badCommandMessage();
        assertEquals("Scegliere un istruzione valida.\n\n", out.toString());
    }
    
    @Test
    public void PrintsEnInOtherCase() throws Exception {
        ui = new Ui(reader, printStream, "invalid");
        ui.badCommandMessage();
        assertEquals("Please enter a valid command.\n\n", out.toString());
    }
    
    @Test
    public void PrintsBadCommandMessage() throws Exception {
        ui.badCommandMessage();
        assertEquals(em.badCommandMessage() + "\n", out.toString());
    }

    @Test
    public void PrintsEmptySpace() throws Exception {
        ui.emptySpace();
        assertEquals("\n", out.toString());
    }

    @Test
    public void DisplaysPokemonWithNullHeight() throws Exception {
        ui.displayPokemon(new Pokemon("Bulbasaur", "Hana", 13, null, null,
                    "18/08/2016", "Cinnabar Island", 22));
        assertEquals("Hana - lv.13 Bulbasaur\n", out.toString());
    }

    @Test
    public void DisplaysPokemon() throws Exception {
        ui.displayPokemon(new Pokemon("Bulbasaur", "Hana", 13, 7, 69,
                    "18/08/2016", "Cinnabar Island", 22));
        assertEquals("~Hana~ lv.13 Bulbasaur - 0.7m, 6.9kg - 22HP - caught on " + 
                "18/08/2016 at Cinnabar Island\n", out.toString());
    }

    @Test
    public void GetsLevel() throws Exception {
        Ui ui = makeUiWithInputStreamString("3\n");
        assertEquals(new Integer(3), ui.getLevel());
        assertEquals(em.levelRequestMessage() + "\n", out.toString());
    }

    @Test
    public void AsksAgainIfCantParseNumber() throws Exception {
        Ui ui = makeUiWithInputStreamString("three\n3\n");
        assertEquals(new Integer(3), ui.getLevel());
        assertEquals(em.levelRequestMessage() + "\n" +
                em.levelRequestMessage() + "\n", out.toString());
    }

    @Test
    public void RejectsLevelOutsideRange() throws Exception {
        Ui ui = makeUiWithInputStreamString("999\n0\n3\n");
        assertEquals(new Integer(3), ui.getLevel());
    }

    @Test
    public void GetsSpecies() throws Exception {
        Ui ui = makeUiWithInputStreamString("Bulbasaur");
        assertEquals("Bulbasaur", ui.getSpecies());
        assertEquals(em.speciesRequestMessage() + "\n", out.toString());
    }

    @Test
    public void GetsNickname() throws Exception {
        Ui ui = makeUiWithInputStreamString("Hana");
        assertEquals("Hana", ui.getNickname());
        assertEquals(em.nicknameRequestMessage() + "\n", out.toString());
    }

    @Test
    public void GetsLocationCaught() throws Exception {
        Ui ui = makeUiWithInputStreamString("Cinnabar Island");
        assertEquals("Cinnabar Island", ui.getLocationCaught());
        assertEquals(em.locationCaughtRequestMessage() + "\n", out.toString());
    }

    @Test
    public void GetsCurrentHp() throws Exception {
        Ui ui = makeUiWithInputStreamString("356\n");
        assertEquals(new Integer(356), ui.getCurrentHp());
        assertEquals(em.currentHpRequestMessage() + "\n", out.toString());
    }

    @Test
    public void GetsDateCaught() throws Exception {
        Ui ui = makeUiWithInputStreamString("18/08/2016\n");
        assertEquals("18/08/2016", ui.getDateCaught());
        assertEquals(em.dateCaughtRequestMessage() + "\n", out.toString());
    }

    @Test
    public void RejectsFutureDate() throws Exception {
        Ui ui = makeUiWithInputStreamString("18/08/2050\n18/08/2010");
        assertEquals("18/08/2010", ui.getDateCaught());
    }

    @Test
    public void PrintsStoreSuccess() throws Exception {
        ui.storeSuccessMessage();
        assertEquals(em.storeSuccessMessage() + "\n\n", out.toString());
    }

    @Test
    public void PrintsSaveSuccess() throws Exception {
        ui.saveSuccessMessage();
        assertEquals(em.saveSuccessMessage() + "\n\n", out.toString());
    }

    @Test
    public void DisplaysSpecies() throws Exception {
        ui.displaySpecies(new Species("Bulbasaur", 5, 10));
        assertEquals(em.speciesFieldname() + ": Bulbasaur\n" +
                     em.heightFieldname() + ": 5\n" +
                     em.weightFieldname() + ": 10\n", out.toString());
    }

    @Test
    public void DisplaysNoneFoundMessage() throws Exception {
        ui.noneFoundMessage();
        assertEquals(em.noneFoundMessage() + "\n\n", out.toString());
    }

    @Test
    public void GetsSpeciesSearchInput() throws Exception {
        Ui ui = makeUiWithInputStreamString("Bulbasaur\n");
        String inputString = ui.getSpeciesSearchInput();
        assertEquals("Bulbasaur", inputString);
        assertEquals(em.searchMessage() + "\n", out.toString());
    }

    private Ui makeUiWithInputStreamString(String inputString) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new ByteArrayInputStream(inputString.getBytes())));
        return new Ui(reader, printStream, "en");
    }
}
