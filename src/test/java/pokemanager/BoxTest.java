package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class BoxTest {
    @Test
    public void TestRetrievePrintsInputStream() throws Exception {
        InputStream inputStream = new ByteArrayInputStream("Charmander\nEmber\nSquirtle\nMizu".getBytes());
        Box box = new Box(inputStream);
        assertEquals("Ember\n(Charmander)\nMizu\n(Squirtle)\n", box.retrieve());
    }

    @Test
    public void TestRetrievePrintsInputStreamPlusStoredPokemon() throws Exception {
        InputStream inputStream = new ByteArrayInputStream("Charmander\nEmber\nSquirtle\nMizu".getBytes());
        Box box = new Box(inputStream);
        box.store("Koffing Cloud");
        box.store("Lapras Shell");
        assertEquals("Ember\n(Charmander)\nMizu\n(Squirtle)\nCloud\n(Koffing)\nShell\n(Lapras)\n", box.retrieve());
    }

    @Test
    public void TestItReturnsPokemonAsDataString() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("Charmander\nEmber\nSquirtle\nMizu".getBytes());
        Box box = new Box(inputStream);
        box.store("Koffing Cloud");
        box.store("Lapras Shell");
        assertEquals("Charmander\nEmber\nSquirtle\nMizu\nKoffing\nCloud\nLapras\nShell\n", box.getDataString());
    }

    @Test
    public void TestBoxClosesInputStreamAfterConstruction() throws Exception {
        ByteArrayInputStreamSpy byteArrayInputStreamSpy = new ByteArrayInputStreamSpy("".getBytes());
        Box box = new Box(byteArrayInputStreamSpy);
        assertTrue(byteArrayInputStreamSpy.closeCalled);
    }
}
