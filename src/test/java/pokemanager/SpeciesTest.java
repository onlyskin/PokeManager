package pokemanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpeciesTest {
    public final Species name = new Species("Bulbasaur", 7, 69);

    @Test
    public void MakesSpeciesString() throws Exception {
        assertEquals("Species: Bulbasaur\nHeight: 7\nWeight: 69\n", name.toString());
    }
}
