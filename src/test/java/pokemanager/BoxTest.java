package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class BoxTest {
    @Test
    public void TestRetrievePrintsInputStream() throws Exception {
        InputStream inputStream = new ByteArrayInputStream("Charmander\nSquirtle".getBytes());
        Box box = new Box(inputStream);
        assertEquals("Charmander\nSquirtle", box.retrieve());
    }

    @Test
    public void TestRetrievePrintsInputStreamPlusStoredPokemon() throws Exception {
        InputStream inputStream = new ByteArrayInputStream("Charmander\nSquirtle".getBytes());
        Box box = new Box(inputStream);
        box.store("Koffing");
        box.store("Lapras");
        assertEquals("Charmander\nSquirtle\nKoffing\nLapras", box.retrieve());
    }

    @Test
    public void TestBoxClosesInputStreamAfterConstruction() throws Exception {
        ByteArrayInputStreamSpy byteArrayInputStreamSpy = new ByteArrayInputStreamSpy("".getBytes());
        Box box = new Box(byteArrayInputStreamSpy);
        assertTrue(byteArrayInputStreamSpy.closeCalled);
    }
}
