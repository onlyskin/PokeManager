package pokemanager;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AppTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
    private final BoxSpy box = new BoxSpy();

    @Test
    public void TestItCallsRetrieveOnBox() {
        InputStream input = new ByteArrayInputStream("box".getBytes());
        App app = new App(input, pw, box);
        app.acceptInput();
        assertTrue(box.retrieveCalled);
    }

    @Test
    public void TestItPrintsRetrievedContents() {
        InputStream input = new ByteArrayInputStream("box".getBytes());
        App app = new App(input, pw, box);
        app.acceptInput();
        assertEquals("Bulbasaur\n", out.toString());
    }

    @Test
    public void TestItCallsStoreWithPokemonNameOnBox() {
        InputStream input = new ByteArrayInputStream("store Charmander".getBytes());
        App app = new App(input, pw, box);
        app.acceptInput();
        assertEquals(box.stored, "Charmander");
    }
}
