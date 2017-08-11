package pokemanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class PokemonTest {
    public final Pokemon pokemon = new Pokemon("Bulbasaur", 7, 69);

    @Test
    public void MakesPokemonString() throws Exception {
        assertEquals("Species: Bulbasaur\nHeight: 7\nWeight: 69\n", pokemon.toString());
    }
}
