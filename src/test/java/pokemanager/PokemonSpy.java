package pokemanager;

public class PokemonSpy extends Pokemon {
    public boolean prettyStringCalled;

    public PokemonSpy() {
        super(null, null, null);
        prettyStringCalled = false;
    }

    @Override
    public String prettyString() {
        prettyStringCalled = true;
        return "";
    }
}