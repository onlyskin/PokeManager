package pokemanager;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BoxTest {
    @Test
    public void TestRetrievePrintsInputStream() {
        InputStream input = new ByteArrayInputStream("Charmander\nSquirtle".getBytes());
        Box box = new Box(input);
        assertEquals("Charmander\nSquirtle", box.retrieve());
    }
}
