package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;

public class RetrieveCommandTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream pw = new PrintStream(out);
    private final PokemonSpy pokemonSpy = new PokemonSpy();
    private final BoxSpy boxSpy = new BoxSpy(pokemonSpy);
    private final RetrieveCommand rc = new RetrieveCommand();
    private final InputStream in = new ByteArrayInputStream("".getBytes());
    private final App app = new App(in, pw, boxSpy, null);

    public RetrieveCommandTest() throws IOException {}

    @Test
    public void CallsRetrieveOnBox() throws Exception {
        rc.execute("box", app);
        assertTrue(boxSpy.retrieveCalled);
    }

    @Test
    public void CallsPrettyStringOnPokemon() throws Exception {
        rc.execute("box", app);
        assertTrue(pokemonSpy.prettyStringCalled);
    }

    @Test
    public void RespondsToBox() throws Exception {
        assertTrue(rc.respondsTo("box"));
    }
}
