package pokemanager;

import org.junit.Test;
import static org.junit.Assert.*;

public class PokemonTest {
    private final Pokemon pokemon1 = new Pokemon("Bulbasaur", "Hana", 5, 7, 69,
            "18/08/2016", "Cinnabar Island", 22);

    @Test
    public void CanGetSpeciesNicknameAndLevel() {
        assertEquals("Bulbasaur", pokemon1.getSpecies());
        assertEquals("Hana", pokemon1.getNickname());
        assertEquals(new Integer(5), pokemon1.getLevel());
        assertEquals(new Integer(7), pokemon1.getHeight());
        assertEquals(new Integer(69), pokemon1.getWeight());
        assertEquals("18/08/2016", pokemon1.getDateCaught());
        assertEquals("Cinnabar Island", pokemon1.getLocationCaught());
        assertEquals(new Integer(22), pokemon1.getCurrentHp());
    }
}
