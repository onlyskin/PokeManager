package pokemanager;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class BoxSpy implements Box {
    public boolean retrieveCalled;
    public boolean storeCalled;
    public boolean saveCalled;
    public List<Pokemon> stored = new ArrayList<Pokemon>();

    public BoxSpy() throws IOException {
        retrieveCalled = false;
        storeCalled = false;
        saveCalled = false;
    }

    public BoxSpy(PokemonSpy pokemonSpy) throws IOException {
        retrieveCalled = false;
        storeCalled = false;
        saveCalled = false;
        stored.add(pokemonSpy);
    }

    @Override
    public List<Pokemon> retrieve() {
        retrieveCalled = true;
        return stored;
    }

    @Override
    public void save() {
        saveCalled = true;
    }

    @Override
    public void store(Pokemon pokemon) {
        storeCalled = true;
        stored.add(pokemon);
    }
}
