package pokemanager;

import org.junit.Test;
import static org.junit.Assert.*;

public class PokemonTest {
    private final Pokemon pokemon0 = new Pokemon("Bulbasaur", "Hana", 5);
    private final Pokemon pokemon1 = new Pokemon("Bulbasaur", "Hana", 5, 7, 69);

    @Test
    public void CanGetSpeciesNicknameAndLevel() {
        assertEquals("Bulbasaur", pokemon0.getSpecies());
        assertEquals("Hana", pokemon0.getNickname());
        assertEquals(new Integer(5), pokemon0.getLevel());
        assertEquals("Bulbasaur", pokemon1.getSpecies());
        assertEquals("Hana", pokemon1.getNickname());
        assertEquals(new Integer(5), pokemon1.getLevel());
        assertEquals(new Integer(7), pokemon1.getHeight());
        assertEquals(new Integer(69), pokemon1.getWeight());
    }
}
