package pokemanager;

import java.io.IOException;
import java.io.InputStream;

public class BoxSpy implements Box {
    public boolean retrieveCalled;
    public boolean storeCalled;
    public boolean saveCalled;
    public String speciesArg;
    public String nicknameArg;
    public Integer levelArg;

    public BoxSpy() throws IOException {
        retrieveCalled = false;
        storeCalled = false;
        saveCalled = false;
    }

    @Override
    public String retrieve() {
        retrieveCalled = true;
        return "Bulbasaur\nHana";
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
}
