package pokemanager;

public class PokemonStub extends Pokemon {
    public PokemonStub() {
        super("", 0, 0);
    }

    @Override
    public String toString() {
        return "Name: Bulbasaur\nHeight: 7\nWeight: 69";
    }
}
