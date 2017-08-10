package pokemanager;

public class ApiSearcherSpy extends ApiSearcher {
    public boolean findDetailsCalled;
    public String calledWith;
    
    public ApiSearcherSpy() {
        super(null);
        findDetailsCalled = true;
    }

    @Override
    public Pokemon findDetails(String pokemon) {
        calledWith = pokemon;
        findDetailsCalled = true;
        return new PokemonStub();
    } 
}
