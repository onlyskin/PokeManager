package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.*;

public class SpeciesFinderTest {
    private final HttpGetRequesterSpy getRequester = new HttpGetRequesterSpy();
    private final SpeciesFinder speciesFinder = new SpeciesFinder(getRequester);

    @Test
    public void CallsGetOnGetRequester() throws Exception {
        speciesFinder.findDetails("");
        assertTrue(getRequester.getCalled); 
    }

    @Test
    public void PassesLowercaseNameIntoGetRequester() throws Exception {
        speciesFinder.findDetails("Bulbasaur");
        assertEquals("bulbasaur", getRequester.calledWith);
    }

    @Test
    public void MakesSpeciesFromGetResponse() throws Exception {
        Species result = speciesFinder.findDetails("");
        assertEquals("bulbasaur", result.getSpecies());
        assertEquals(new Integer(7), result.getHeight());
        assertEquals(new Integer(69), result.getWeight());
    }
    
    @Ignore
    @Test
    public void ReturnsNullIfApiNotFound() throws Exception {
        Species result = speciesFinder.findDetails("bad_input");
        assertNull(result);
    }
}
