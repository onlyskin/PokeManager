package pokemanager;

import org.junit.Test;
import static org.junit.Assert.*;

public class PokemonTest {
    private final Pokemon pokemon = new Pokemon("Bulbasaur", "Hana", 5);

    @Test
    public void OutputsJson() {
        assertEquals("{\"level\":5,\"species\":\"Bulbasaur\",\"nickname\":\"Hana\"}", pokemon.toJSONString());
    }

    @Test
    public void CanGetSpeciesNicknameAndLevel() {
        assertEquals("Bulbasaur", pokemon.getSpecies());
        assertEquals("Hana", pokemon.getNickname());
        assertEquals(new Integer(5), pokemon.getLevel());
    }
}
