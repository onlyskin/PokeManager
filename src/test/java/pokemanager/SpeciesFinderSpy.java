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
        } else {
            return new SpeciesStub();
        }
    } 
}
