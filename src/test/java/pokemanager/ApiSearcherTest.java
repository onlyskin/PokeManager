package pokemanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApiSearcherTest {
    private final HttpGetRequesterSpy getRequester = new HttpGetRequesterSpy();
    private final ApiSearcher apiSearcher = new ApiSearcher(getRequester);

    @Test
    public void CallsGetOnGetRequester() throws Exception {
        apiSearcher.findDetails("");
        assertTrue(getRequester.getCalled); 
    }

    @Test
    public void PassesLowercasePokemonIntoGetRequester() throws Exception {
        apiSearcher.findDetails("Bulbasaur");
        assertEquals("bulbasaur", getRequester.calledWith);
    }

    @Test
    public void MakesPokemonFromGetResponse() throws Exception {
        Pokemon result = apiSearcher.findDetails("");
        assertEquals("bulbasaur", result.getSpecies());
        assertEquals(new Integer(7), result.getHeight());
        assertEquals(new Integer(69), result.getWeight());
    }
}
