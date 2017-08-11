package pokemanager;

public class SpeciesFinderSpy extends SpeciesFinder {
    public boolean findDetailsCalled;
    public String calledWith;
    
    public SpeciesFinderSpy() {
        super(null);
        findDetailsCalled = false;
    }

    @Override
    public Species findDetails(String name) {
        calledWith = name;
        findDetailsCalled = true;
        if (name.equals("bad_input")) {
            return null;
        } else {
            return new SpeciesStub();
        }
    } 
}
