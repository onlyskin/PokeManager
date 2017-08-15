package pokemanager;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

public class BoxSpy implements Box {
    public boolean retrieveCalled;
    public boolean storeCalled;
    public boolean saveCalled;
    public String speciesArg;
    public String nicknameArg;
    public Integer levelArg;
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
    public void store(String species, String nickname, Integer level) {
        storeCalled = true;
        speciesArg = species;
        nicknameArg = nickname;
        levelArg = level;
    }

    @Override
    public void save() {
        saveCalled = true;
    }

    @Override
    public void store(Pokemon pokemon) {
        storeCalled = true;
        speciesArg = pokemon.getSpecies();
        nicknameArg = pokemon.getNickname();
        levelArg = pokemon.getLevel();
    }
}
