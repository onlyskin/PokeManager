package pokemanager;

public class SpeciesFinderSpy extends SpeciesFinder {
    public boolean findDetailsCalled;
    public String calledWith;
    
    public SpeciesFinderSpy() {
        super(null);
        findDetailsCalled = false;
    }

    @Override
    public Species findDetails(String searchString) {
        calledWith = searchString;
        findDetailsCalled = true;
        if (searchString.equals("bad_input")) {
            return null;
        } else if (searchString.equals("Charmander")) {
            return new Species("Charmander", 6, 85);
        } else {
            return new SpeciesStub();
        }
    } 
}
