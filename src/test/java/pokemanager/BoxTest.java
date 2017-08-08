package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class BoxTest {
    private Box box;

    @Test
    public void RetrievePrintsInputStream() throws Exception {
        makeBoxWithInputString("Charmander\nEmber\nSquirtle\nMizu");
        assertEquals("Ember\n(Charmander)\nMizu\n(Squirtle)\n", box.retrieve());
    }

    @Test
    public void RetrievePrintsStoredPokemon() throws Exception {
        makeBoxWithInputString("Charmander\nEmber\nSquirtle\nMizu");
        box.store("Koffing Cloud");
        box.store("Lapras Shell");
        assertEquals("Ember\n(Charmander)\n" +
                     "Mizu\n(Squirtle)\n" +
                     "Cloud\n(Koffing)\n" +
                     "Shell\n(Lapras)\n", box.retrieve());
    }

    @Test
    public void GetsDataString() throws IOException {
        makeBoxWithInputString("Charmander\nEmber\nSquirtle\nMizu");
        box.store("Koffing Cloud");
        box.store("Lapras Shell");
        assertEquals("Charmander\nEmber\n" +
                "Squirtle\nMizu\n" +
                "Koffing\nCloud\n" +
                "Lapras\nShell\n", box.getDataString());
    }

    @Test
    public void ClosesInputStreamAfterConstruction() throws Exception {
        ByteArrayInputStreamSpy bAISSpy = new ByteArrayInputStreamSpy("".getBytes());
        Box box = new Box(bAISSpy);
        assertTrue(bAISSpy.closeCalled);
    }

    private void makeBoxWithInputString(String inputString) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        box = new Box(inputStream);
    }
}
