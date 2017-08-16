package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;

public class RetrieveCommandTest {

    private final PokemonSpy pokemonSpy = new PokemonSpy();
    private final BoxSpy boxSpy = new BoxSpy(pokemonSpy);
    private final UiSpy uiSpy = new UiSpy();
    private final RetrieveCommand rc = new RetrieveCommand(boxSpy, uiSpy);

    public RetrieveCommandTest() throws IOException {}

    @Test
    public void CallsRetrieveOnBox() throws Exception {
        rc.execute("box");
        assertTrue(boxSpy.retrieveCalled);
    }

    @Test
    public void CallsDisplayPokemonOnUi() throws Exception {
        rc.execute("box");
        assertTrue(uiSpy.displayPokemonCalled);
    }

    @Test
    public void RespondsToBox() throws Exception {
        assertTrue(rc.respondsTo("box"));
    }
}
