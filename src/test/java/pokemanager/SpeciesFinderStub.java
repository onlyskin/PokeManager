package pokemanager;

public class SpeciesFinderStub extends SpeciesFinder {

    public SpeciesFinderStub() {
        super(null);
    }

    @Override
    public Species findDetails(String searchString) {
        return new SpeciesStub();
    }
} 
